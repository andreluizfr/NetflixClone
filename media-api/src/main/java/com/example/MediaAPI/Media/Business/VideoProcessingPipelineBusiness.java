package com.example.MediaAPI.Media.Business;

import com.example.MediaAPI.Media.Models.EpisodeTrack;
import com.example.MediaAPI.Media.Models.DTOs.VideoProcessingPipelineDTO;
import com.example.MediaAPI.Media.Models.Enums.TrackProcessingStatus;
import com.github.kokorin.jaffree.LogLevel;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.FFmpegProgress;
import com.github.kokorin.jaffree.ffmpeg.ProgressListener;
import com.github.kokorin.jaffree.ffmpeg.UrlInput;
import com.github.kokorin.jaffree.ffmpeg.UrlOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoProcessingPipelineBusiness {

    private static final Logger logger = LogManager.getLogger(VideoProcessingPipelineBusiness.class);

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final BlockingQueue<VideoProcessingPipelineDTO> queue = new LinkedBlockingQueue<>();
    //private final Lock lock = new ReentrantLock();
    private boolean isProcessing = false;

    private final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private final TransformerFactory transformerFactory = TransformerFactory.newInstance();

    @Autowired
    MediaBusiness mediaBusiness;

    public void addToQueue(EpisodeTrack episodeTrack, String trackDir, String inputFileName) {

        VideoProcessingPipelineDTO dto = VideoProcessingPipelineDTO
                .builder()
                .episodeTrack(episodeTrack)
                .trackDir(trackDir)
                .inputFileName(inputFileName)
                .build();

        queue.offer(dto);
        dto.getEpisodeTrack().setProcessingStatus(TrackProcessingStatus.IN_QUEUE);
        mediaBusiness.updateEpisodeTrack(dto.getEpisodeTrack());
        logger.info("item adicionado a fila", queue.toString());
        //lock.lock(); //seção crítica
        checkProcessing();
        //lock.unlock();
    }

    public synchronized void checkProcessing() {
        if(!isProcessing) {
            logger.info("Iniciando nova thread para processamento de vídeo.");
            isProcessing = true;
            executorService.submit(this::processQueue);
        } else {
            logger.info("Thread de processamento de vídeo já em processamento. Sem necessidade de criar outra Thread.");
        }
    }

    public void processQueue() {

        VideoProcessingPipelineDTO dto;

        do {
            dto = queue.poll();
            if(dto != null) {
                try {
                    dto.getEpisodeTrack().setProcessingStatus(TrackProcessingStatus.PROCESSING);
                    mediaBusiness.updateEpisodeTrack(dto.getEpisodeTrack());

                    encodeAndGenerateDashManifest(dto.getTrackDir(), dto.getInputFileName());
                    moveChunksToTemporaryFolder(dto.getTrackDir());
                    generateThumbnails(dto.getTrackDir());
                    moveThumbnailsToTemporaryFolder(dto.getTrackDir());
                    addThumbnailsInfoInDashFile(dto.getTrackDir());

                    dto.getEpisodeTrack().setProcessingStatus(TrackProcessingStatus.PROCESSED);
                    mediaBusiness.updateEpisodeTrack(dto.getEpisodeTrack());

                    logger.info("Video processing pipeline for " + dto.getInputFileName() + " has finished successfully!");
                    Thread.sleep(5000);
                } catch (ParserConfigurationException | SAXException | IOException | TransformerException | InterruptedException e) {

                    dto.getEpisodeTrack().setProcessingStatus(TrackProcessingStatus.ERROR);
                    mediaBusiness.updateEpisodeTrack(dto.getEpisodeTrack());
                    logger.error("Error while processing new video: " + dto.getInputFileName());
                    e.printStackTrace();
                }
            }
        } while(dto != null);

        isProcessing = false;
    }

    private void encodeAndGenerateDashManifest(String outputPath, String inputFileName) {

        String inputFile = outputPath + File.separator + inputFileName;
        String outputFile = outputPath + File.separator + "MANIFEST.mpd";

        AtomicLong duration = new AtomicLong();

        FFmpeg ffmpeg = FFmpeg.atPath()
                .addInput(UrlInput.fromUrl(inputFile))

                .addArguments("-map", "0:v:0")
                .addArguments("-map", "0:a:0")
                .addArguments("-map", "0:v:0")
                .addArguments("-map", "0:a:0")
                .addArguments("-map", "0:v:0")
                .addArguments("-map", "0:a:0")

                .addArguments("-b:v:0", "1000k")
                .addArguments("-c:v:0", "libx264")
                .addArguments("-c:a:0", "aac")
                .addArguments("-filter:v:0", "\"scale=-2:360\"")
                .addArguments("-profile:v:0", "baseline")

                .addArguments("-b:v:1", "4000k")
                .addArguments("-c:v:1", "libx264")
                .addArguments("-c:a:1", "aac")
                .addArguments("-filter:v:1", "\"scale=-2:720\"")
                .addArguments("-profile:v:1", "main")

                .addArguments("-b:v:2", "6000k")
                .addArguments("-c:v:2", "libx264")
                .addArguments("-c:a:2", "aac")
                .addArguments("-filter:v:2", "\"scale=-2:1080\"")
                .addArguments("-profile:v:2", "high")

                .addArguments("-use_timeline", "1")
                .addArguments("-use_template", "1")
                .addArguments("-segment_time", "5")
                .addArguments("-seg_duration", "5")
                .addArguments("-sc_threshold", "0")
                .addArguments("-dash_segment_type", "\"mp4\"")
                .addArguments("-adaptation_sets", "\"id=0,streams=v id=1,streams=a\"")
                .addArguments("-f", "dash")

                .addOutput(UrlOutput.toUrl(outputFile))
                .setLogLevel(LogLevel.DEBUG)
                .setOverwriteOutput(true)
                .setProgressListener(new ProgressListener() {
                    @Override
                    public void onProgress(FFmpegProgress progress) {
                        duration.set(progress.getTimeMillis() / 1000);
                    }
                });

        ffmpeg.execute();
        logger.info("Encoding durou: " + duration.get() + "s");
    }

    private void moveChunksToTemporaryFolder(String outputPath) throws IOException {

        String[] prefixs = {"chunk-stream", "init-stream"};
        
        Path sourceDirectory = Paths.get(System.getProperty("user.dir"));
        Path targetDirectory = Paths.get(outputPath);
        
        for (String prefix : prefixs) {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDirectory, path -> path.getFileName().toString().startsWith(prefix));
            for (Path path : directoryStream) {
                Path targetPath = targetDirectory.resolve(path.getFileName());
                Files.move(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
                logger.info("Moved: " + path.getFileName() + " to " + targetPath);
            }
            directoryStream.close();
        }
    }

    private void generateThumbnails(String outputPath) {

        String inputFile = outputPath + File.separator + "MANIFEST.mpd";

        AtomicLong duration = new AtomicLong();

        FFmpeg ffmpeg = FFmpeg.atPath()
                .addInput(UrlInput.fromUrl(inputFile))
                .addArguments("-vf", "\"fps=1/5,scale=-2:90\"")
                .addArgument("thumbnail%03d.jpg")
                .setOverwriteOutput(true)
                .setProgressListener(new ProgressListener() {
                    @Override
                    public void onProgress(FFmpegProgress progress) {
                        duration.set(progress.getTimeMillis() / 1000);
                    }
                });

        ffmpeg.execute();
        logger.info("Criação de thumbnails durou: " + duration.get() + "s");
    }

    private void moveThumbnailsToTemporaryFolder(String outputPath) throws IOException {

        String prefix = "thumbnail";
        
        Path sourceDirectory = Paths.get(System.getProperty("user.dir"));
        Path targetDirectory = Paths.get(outputPath).resolve("thumbnails");
        Files.createDirectories(targetDirectory);
        
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceDirectory, path -> path.getFileName().toString().startsWith(prefix));
        for (Path path : directoryStream) {
            Path targetPath = targetDirectory.resolve(path.getFileName());
            Files.move(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("Moved: " + path.getFileName() + " to " + targetPath);
        }
        directoryStream.close();
    }

    private void addThumbnailsInfoInDashFile(String outputPath) throws ParserConfigurationException, SAXException, IOException, TransformerException {

        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        
        DocumentBuilder db = dbf.newDocumentBuilder();

        File manifestFile = new File (outputPath + File.separator + "MANIFEST.MPD");
        Document doc = db.parse(manifestFile);

        // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        // Create the new AdaptationSet
        Element thumbnailsAdaptationSet = doc.createElement("AdaptationSet");
        thumbnailsAdaptationSet.setAttribute("id", "2");
        thumbnailsAdaptationSet.setAttribute("contentType", "image");
        thumbnailsAdaptationSet.setAttribute("mimeType", "image/jpeg");

        Element baseURL = doc.createElement("BaseURL");
        baseURL.setTextContent("thumbnails/");
        thumbnailsAdaptationSet.appendChild(baseURL);

        Element segmentTemplate = doc.createElement("SegmentTemplate");
        segmentTemplate.setAttribute("duration", "5");
        segmentTemplate.setAttribute("media", "$RepresentationID$/thumbnail$Number%03d$.jpg");
        segmentTemplate.setAttribute("startNumber", "1");
        thumbnailsAdaptationSet.appendChild(segmentTemplate);

        Element representation = doc.createElement("Representation");
        representation.setAttribute("bandwidth", "12895");
        representation.setAttribute("height", "90");
        representation.setAttribute("width", "160");
        representation.setAttribute("id", "thumbnails_widthx90");

        Element essentialProperty = doc.createElement("EssentialProperty");
        essentialProperty.setAttribute("schemeIdUri", "http://dashif.org/guidelines/thumbnail_tile");
        essentialProperty.setAttribute("value", "1x1");
        representation.appendChild(essentialProperty);

        thumbnailsAdaptationSet.appendChild(representation);

        NodeList list = doc.getElementsByTagName("Period");
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            node.appendChild(thumbnailsAdaptationSet);
        }

        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outputPath + File.separator + "MANIFEST_UPDATED.MPD"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        logger.info("Thumbnails adaptation set added to dash file: " + thumbnailsAdaptationSet.toString());
    }
}
