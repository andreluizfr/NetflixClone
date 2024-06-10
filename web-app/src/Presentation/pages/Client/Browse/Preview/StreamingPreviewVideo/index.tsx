import './styles.scss';

import dashjs from "dashjs";
import { ClipLoader } from 'react-spinners';

import { motion } from "framer-motion";
import { useEffect, useRef } from "react";
import { useMediaQuery } from 'react-responsive';
import { TrackMetadata } from '@Model/entities/Track';
import { Media } from '@Model/entities/Media';

interface props {
    trackMetadata: TrackMetadata;
    media: Media;
}

export default function StreamingPreviewVideo ({trackMetadata, media}: props) {

    const iconLoading = useRef<HTMLDivElement>(null);
    const video = useRef<HTMLVideoElement>(null);
    const videoContainer = useRef<HTMLDivElement>(null);

    let firstTimePlay = true;

    const player = dashjs.MediaPlayer().create();
    player.updateSettings({
        streaming: {
            abr: {
                useDefaultABRRules: true,
                ABRStrategy: 'abrDynamic',
                additionalAbrRules: {
                    insufficientBufferRule: true,
                    switchHistoryRule: false,
                    droppedFramesRule: false,
                    abandonRequestsRule: false
                },
                autoSwitchBitrate: {
                    video: true,
                    audio: true
                }
                //maxBitrate: { audio: -1, video: 5000 },  //ideal para limitar qualidade por perfil
                //minBitrate: { audio: -1, video: 2000 },  //ideal para limitar qualidade por perfil
            },
            scheduling: {
                scheduleWhilePaused: true,   /* load segments while paused */
            },
            buffer: {
                //When an up-switch in quality is performed, the next fragment is not appended at the end of the current buffer range but closer to the current time
                //When ABR down-switch is detected, we appended the lower quality at the end of the buffer range to preserve the higher quality media for as long as possible
                fastSwitchEnabled: true,
                bufferPruningInterval: 10, //intervalo de tempo entre verificação para remover buffers desnecessario
                bufferToKeep: 20, //quantidade mínima de dados do bufer a ser mantida durante a reprodução
                bufferTimeAtTopQuality: 30, //define por quanto tempo, em segundos, o buffer deve ser mantido quando a qualidade do vídeo está no nível mais alto
                bufferTimeAtTopQualityLongForm: 60,
                stableBufferTime: 30, //tempo em segundos, que o buffer deve ser mantido para garantir estabilidade na reprodução
            }
        }
    });

    const isSmall = useMediaQuery({
        query: '(max-width: 576px)'
    });
    const isMedium = useMediaQuery({
        query: '(max-width: 720px)'
    });
    const isLarge = useMediaQuery({
        query: '(max-width: 956px)'
    });

    useEffect(()=>{
        if(video.current){
            video.current.poster = media.posterUrl;
            if(import.meta.env.DEV) {
                player.initialize(video.current, "streamingPreviews/" + trackMetadata.id + "/MANIFEST_UPDATED.MPD", false, 0);
            } else {
                player.initialize(video.current, "https://d1hlsc6afcywvp.cloudfront.net/streamingPreviews/" + trackMetadata.id + "/MANIFEST_UPDATED.MPD", false, 0);
            }
            addPlayerEventsListeners();
            player.setMute(true);
            player.play();
        }
        return (()=>{
            removePlayerEventsListeners();
        });
    }, [video.current]);

    function addPlayerEventsListeners() {
        player.on(dashjs.MediaPlayer.events.PLAYBACK_WAITING, ()=>showLoading());
        player.on(dashjs.MediaPlayer.events.PLAYBACK_ENDED, ()=>restartVideo());
    };

    function removePlayerEventsListeners() {
        player.off(dashjs.MediaPlayer.events.PLAYBACK_WAITING, ()=>showLoading());
        player.off(dashjs.MediaPlayer.events.PLAYBACK_ENDED, ()=>restartVideo());
    };

    function restartVideo() {
        player.play();
    }

    function showLoading() {
        if(iconLoading.current !== null && !firstTimePlay) {
            iconLoading.current.classList.add('icon-loading');
            iconLoading.current.classList.remove('icon-not-loading');
        }
    }


    return (
        <motion.div
            className="StreamingPreviewVideo"
            initial={{width: 0, height: 0, opacity: 0}}
            animate={{width: "100vw", height: "100%", opacity: 1}}
        >
            <div className="dash-video-player">
                <div className="videoContainer" ref={videoContainer} id="videoContainer">

                    <video preload="auto" width="100vw" height="auto" ref={video}></video>

                    <div id="loading" className='icon-not-loading' ref={iconLoading}>
                        <ClipLoader className='Loader'
                            color="rgb(229,9,20)"
                            size={isSmall?(40):isMedium?(50):isLarge?(60):(70)}
                            aria-label="Loading Spinner"
                            data-testid="loader"
                        />
                    </div>

                </div>
            </div>
        </motion.div>
    );
}