import './styles.css';

import dashjs from "dashjs";
import { ClipLoader } from 'react-spinners';

import { motion } from "framer-motion";
import { useEffect, useRef } from "react";
import { useMediaQuery } from 'react-responsive';
import { Track } from '@Model/entities/Track';
import { Media } from '@Model/entities/Media';

interface props {
    track: Track;
    media: Media;
}

export default function TrackPage ({track, media}: props) {

    console.log("media=", media,",track=", track);

    const videoController = useRef<HTMLDivElement>(null);
    const playPauseBtn = useRef<HTMLDivElement>(null);
    const iconPlayPause = useRef<HTMLSpanElement>(null);
    const captionBtn = useRef<HTMLDivElement>(null);
    const seekbar = useRef<HTMLDivElement>(null);
    const seekbarPlay = useRef<HTMLDivElement>(null);
    const seekbarBuffer = useRef<HTMLDivElement>(null);
    const muteBtn = useRef<HTMLDivElement>(null);
    const volumebar = useRef<HTMLInputElement>(null);
    const fullscreenBtn = useRef<HTMLDivElement>(null);
    const timeDisplay = useRef<HTMLSpanElement>(null);
    const durationDisplay = useRef<HTMLSpanElement>(null);
    const thumbnailContainer = useRef<HTMLDivElement>(null);
    const thumbnailElem = useRef<HTMLDivElement>(null);
    const thumbnailTimeLabel = useRef<HTMLDivElement>(null);
    const iconLoading = useRef<HTMLDivElement>(null);
    const captionMenu = useRef<HTMLElement>(null);
    const bitrateListMenu = useRef<HTMLElement>(null);
    const trackSwitchMenu = useRef<HTMLElement>(null);
    const middlePlayBtn = useRef<HTMLDivElement>(null);

    // let menuHandlersList = {
    //     bitrate: null,
    //     caption: null,
    //     track: null
    // } as {
    //     bitrate: null | Function,
    //     caption: null | Function,
    //     track: null | Function
    // };
    let seeking = false;
    let videoControllerVisibleTimeout: NodeJS.Timeout;
    // let liveThresholdSecs = 1;
    // let textTrackList = {} as  {
    //     [key: string]: any,
    // };
    // let forceQuality = false;
    const video = useRef<HTMLVideoElement>(null);
    const videoContainer = useRef<HTMLDivElement>(null);
    // let nativeTextTracks = useRef<HTMLDivElement>(null);
    // let idSuffix: number;
    // let seekbarBufferInterval: number; 

    // Maximum percentage of player height that the thumbnail will fill (between 0 and 1)
    var maxPercentageThumbnailScreen = 0.15; 
    // Maximum scale so small thumbs are not scaled too high
    var maximumScale = 1.5;

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
                stableBufferTime: 10, //tempo em segundos, que o buffer deve ser mantido para garantir estabilidade na reprodução
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
        //if(video.current && getTrackServiceResult.data?.data){
        if(video.current){
            if(import.meta.env.DEV) {
                player.initialize(video.current, "tracks/1/manifest.mpd", false, 0);
            }
            else {
                player.initialize(video.current, "https://netflix-clone-2.s3.sa-east-1.amazonaws.com/tracks/id-teste-1/manifest.mpd", false, 0);
            }
            addPlayerEventsListeners();
        }
        return (()=>{
            removePlayerEventsListeners();
        });
    }, [video.current]);

    function addPlayerEventsListeners() {
        player.on(dashjs.MediaPlayer.events.CAN_PLAY, ()=>updateDuration());
        player.on(dashjs.MediaPlayer.events.PLAYBACK_STARTED, ()=>adjustPlayPauseBtnState());
        player.on(dashjs.MediaPlayer.events.PLAYBACK_PAUSED, ()=>adjustPlayPauseBtnState());
        player.on(dashjs.MediaPlayer.events.PLAYBACK_TIME_UPDATED, ()=>onPlayTimeUpdate());
        player.on(dashjs.MediaPlayer.events.PLAYBACK_WAITING, ()=>showLoading());

        //player.on(dashjs.MediaPlayer.events.PLAYBACK_ENDED, ()=>goToNextEpisode());

        //player.on(dashjs.MediaPlayer.events.PLAYBACK_RATE_CHANGED, ()=>updateRateText());
        //player.on(dashjs.MediaPlayer.events.QUALITY_CHANGE_RENDERED, ()=>updateRateText());

        // player.on(dashjs.MediaPlayer.events.STREAM_ACTIVATED, (e)=>onStreamActivated(e));
        // player.on(dashjs.MediaPlayer.events.STREAM_DEACTIVATED, (e)=>onStreamDeactivated(e));
        // player.on(dashjs.MediaPlayer.events.STREAM_TEARDOWN_COMPLETE, (e)=>onStreamTeardownComplete(e));
        // player.on(dashjs.MediaPlayer.events.TEXT_TRACKS_ADDED, ()=>onTracksAdded());
        player.on(dashjs.MediaPlayer.events.BUFFER_LEVEL_UPDATED, ()=>onBufferLevelUpdated());

        document.addEventListener('fullscreenchange', ()=>onFullScreenChange());
    };

    function removePlayerEventsListeners() {
        player.off(dashjs.MediaPlayer.events.CAN_PLAY, ()=>updateDuration());
        player.off(dashjs.MediaPlayer.events.PLAYBACK_STARTED, ()=>adjustPlayPauseBtnState());
        player.off(dashjs.MediaPlayer.events.PLAYBACK_PAUSED, ()=>adjustPlayPauseBtnState());
        player.off(dashjs.MediaPlayer.events.PLAYBACK_TIME_UPDATED, ()=>onPlayTimeUpdate());
        player.off(dashjs.MediaPlayer.events.PLAYBACK_WAITING, ()=>showLoading());
        //player.off(dashjs.MediaPlayer.events.STREAM_ACTIVATED, ()=>onStreamActivated());
        //player.off(dashjs.MediaPlayer.events.STREAM_DEACTIVATED, ()=>onStreamDeactivated());
        //player.off(dashjs.MediaPlayer.events.STREAM_TEARDOWN_COMPLETE, ()=>onStreamTeardownComplete());
        //player.off(dashjs.MediaPlayer.events.TEXT_TRACKS_ADDED, ()=>onTracksAdded());
        player.off(dashjs.MediaPlayer.events.BUFFER_LEVEL_UPDATED, ()=>onBufferLevelUpdated());

        document.removeEventListener('fullscreenchange', ()=>onFullScreenChange());
    };

    //************************************************************************************
    // TIME/DURATION
    //************************************************************************************
    var setDuration = function (value: number) {
        if(durationDisplay.current)
            durationDisplay.current.textContent = player.convertToTimeCode(value);
    };

    function setTime (value: number) {
        if (value < 0) {
            return;
        }
        if(timeDisplay.current)
            timeDisplay.current.textContent = player.convertToTimeCode(value);
        updateDuration();
    };

    function updateDuration () {
        var duration = player.duration();
        setDuration(duration);
    };

    //************************************************************************************
    // PLAYBACK
    //************************************************************************************

    function togglePlayPauseVideoState () {
        player.isPaused() ? player.play() : player.pause();
    };

    function adjustPlayPauseBtnState () {
        player.isPaused() ? setPlayBtn() : setPauseBtn();
    };

    function setPlayBtn () {
        if (iconPlayPause.current !== null) {
            iconPlayPause.current.classList.remove('icon-pause');
            iconPlayPause.current.classList.add('icon-play');
        }
    };

    function setPauseBtn () {
        if (iconPlayPause.current !== null) {
            iconPlayPause.current.classList.remove('icon-play');
            iconPlayPause.current.classList.add('icon-pause');
        }
    };

    function handleFirstTimePlay() {
        if (middlePlayBtn.current !== null) {
            middlePlayBtn.current.classList.remove('icon-middle-play-btn-on');
            middlePlayBtn.current.classList.add('icon-middle-play-btn-off');

            videoController.current?.classList.remove('hide');

            togglePlayPauseVideoState(); 

            firstTimePlay=false;
        }
    }

    function getBufferLevel (player: dashjs.MediaPlayerClass) {
        var bufferLevel = 0;
        if (player.getDashMetrics) {
            var dashMetrics = player.getDashMetrics();
            if (dashMetrics) {
                bufferLevel = dashMetrics.getCurrentBufferLevel('video');
                if (!bufferLevel) {
                    bufferLevel = dashMetrics.getCurrentBufferLevel('audio');
                }
            }
        }
        return bufferLevel;
    };

    function onBufferLevelUpdated () {
        if (seekbarBuffer.current) {
            seekbarBuffer.current.style.width = ((player.time() + getBufferLevel(player)) / player.duration() * 100) + '%';
        }
    };

    function onPlayTimeUpdate () {
        if (!seeking) {
            setTime(player.time());
            if (seekbarPlay.current) {
                seekbarPlay.current.style.width = (player.time() / player.duration() * 100) + '%';
            }
            //removing loading
            if(iconLoading.current !== null) {
                iconLoading.current.classList.remove('icon-loading');
                iconLoading.current.classList.add('icon-not-loading');
            }
        }
    };

    //************************************************************************************
    // FULLSCREEN
    //************************************************************************************

    function onFullScreenChange() {
        if(fullscreenBtn.current) {
            var icon;
            if (isFullscreen()) {
                enterFullscreen();
                icon = fullscreenBtn.current.querySelector('.icon-fullscreen-enter');
                if(icon) {
                    icon.classList.remove('icon-fullscreen-enter');
                    icon.classList.add('icon-fullscreen-exit');
                }
                
            } else {
                exitFullscreen();
                icon = fullscreenBtn.current.querySelector('.icon-fullscreen-exit');
                if(icon) {
                    icon.classList.remove('icon-fullscreen-exit');
                    icon.classList.add('icon-fullscreen-enter');
                }   
            }
        }
    };

    function isFullscreen () {
        return document.fullscreenElement;
    };

    function enterFullscreen() {
        var element = videoContainer.current || video.current;
        if (!document.fullscreenElement && element) {
            if (element.requestFullscreen) {
                element.requestFullscreen();
            } 
        }

        videoController.current?.classList.add('video-controller-fullscreen');
        window.addEventListener('mousemove', ()=>onFullScreenMouseMove());
        onFullScreenMouseMove();
    };

    function clearFullscreenState() {
        if(videoController.current) {
            clearTimeout(videoControllerVisibleTimeout);
            videoController.current.classList.remove('hide');
        }
    };

    function onFullScreenMouseMove() {
        if(videoController.current) {
            clearFullscreenState();
            videoControllerVisibleTimeout = setTimeout(function () {
                videoController.current?.classList.add('hide');
            }, 4000);
        }
    };

    function exitFullscreen() {
        if(videoController.current) {
            window.removeEventListener('mousemove', onFullScreenMouseMove);
            clearFullscreenState();

            if (document.fullscreenElement && document.exitFullscreen) {
                document.exitFullscreen();
            }
        }
    };

    var onFullscreenClick = function () {
        if (!isFullscreen()) {
            enterFullscreen();
        } else {
            exitFullscreen();
        }
        if (captionMenu.current) {
            captionMenu.current.classList.add('hide');
        }
        if (bitrateListMenu.current) {
            bitrateListMenu.current.classList.add('hide');
        }
        if (trackSwitchMenu.current) {
            trackSwitchMenu.current.classList.add('hide');
        }
    };

     //************************************************************************************
    // VOLUME
    //************************************************************************************

    function setVolume(e: React.ChangeEvent<HTMLInputElement>) {
        if(volumebar.current){
            player.setVolume(parseFloat(e.target.value)/100);
            player.setMute(player.getVolume() === 0);
            toggleMuteBtnState();
        }
    };

    function onMuteClick () {
        if(volumebar.current) {
            console.log("onMuteClick");
            if (player.isMuted()) {
                console.log(volumebar.current.value);
                player.setVolume(parseFloat(volumebar.current.value)/100);
            } else {
                console.log(0);
                player.setVolume(0);
            }
            player.setMute(player.getVolume() === 0);
            toggleMuteBtnState();
        }   
    }

    function toggleMuteBtnState () {
        if(muteBtn.current){
            const muteBtnIcon = muteBtn.current.querySelector('#iconMute');
            if(muteBtnIcon){
                if (player.isMuted()) {
                    muteBtnIcon.classList.add('icon-mute-off');
                    muteBtnIcon.classList.remove('icon-mute-on');
                } else {
                    muteBtnIcon.classList.add('icon-mute-on');
                    muteBtnIcon.classList.remove('icon-mute-off');
                }
            }
        }
    };

    //************************************************************************************
    // SEEKING/THUMBNAIL
    // ************************************************************************************

    function calculateTimeByEvent(event: React.MouseEvent<HTMLDivElement, MouseEvent> | React.TouchEvent<HTMLDivElement>) {
        if(seekbar.current){
            var seekbarRect = seekbar.current.getBoundingClientRect();
            if(event.nativeEvent instanceof MouseEvent) {
                return Math.floor(player.duration() * (event.nativeEvent.clientX - seekbarRect.left) / seekbarRect.width);
            } else if(event.nativeEvent instanceof TouchEvent) {
                return Math.floor(player.duration() * (event.nativeEvent.touches[0].clientX - seekbarRect.left) / seekbarRect.width);
            }
        }
        return 0;
    };

    function onSeeking(event: React.MouseEvent<HTMLDivElement, MouseEvent> | React.TouchEvent<HTMLDivElement>) {
        if(seekbar.current) {
            seeking = true;
            var mouseTime = calculateTimeByEvent(event);
            if (seekbarPlay.current) {
                seekbarPlay.current.style.width = (mouseTime / player.duration() * 100) + '%';
            }
        }
    };

    function onSeeked(event: React.MouseEvent<HTMLDivElement, MouseEvent> | React.TouchEvent<HTMLDivElement>) {
        if(seekbar.current && seeking === true) {
            seeking = false;
            var mouseTime = calculateTimeByEvent(event);
            if (!isNaN(mouseTime)) {
                mouseTime = mouseTime < 0 ? 0 : mouseTime;
                player.seek(mouseTime);
            }
            if (seekbarPlay.current) {
                seekbarPlay.current.style.width = (mouseTime / player.duration() * 100) + '%';
            }
        }
    };

    function onSeekBarMouseMove(event: React.MouseEvent<HTMLDivElement, MouseEvent> | React.TouchEvent<HTMLDivElement>) {
        if(seekbar.current && videoController.current && seekbarPlay.current && seeking === true){
            // Take into account page offset and seekbar position
            var mouseTime = calculateTimeByEvent(event);
            if (isNaN(mouseTime)) return;

            // Update timer and play progress bar if mousedown (mouse click down)
            setTime(mouseTime);
            seekbarPlay.current.style.width = (mouseTime / player.duration() * 100) + '%';
        }
        showThumb(event);
    };

    function showThumb(event: React.MouseEvent<HTMLDivElement, MouseEvent> | React.TouchEvent<HTMLDivElement>) {
        if (player.provideThumbnail && thumbnailElem.current && thumbnailContainer.current && seekbar.current && videoContainer.current && videoController.current) {
            var videoContainerRect = videoContainer.current.getBoundingClientRect();
            var videoControllerRect = videoController.current.getBoundingClientRect();
            var seekbarRect = seekbar.current.getBoundingClientRect();

            // Calculate time position given mouse position
            if(event.nativeEvent instanceof MouseEvent){
                var left = event.nativeEvent.clientX - seekbarRect.left;
            } else if(event.nativeEvent instanceof TouchEvent) {
                var left = event.nativeEvent.touches[0].clientX - seekbarRect.left;
            }

            var mouseTime = calculateTimeByEvent(event);
            if (isNaN(mouseTime)) return;

            player.provideThumbnail(mouseTime, function (thumbnail) {

                if (!thumbnail) return;

                // Adjust left variable for positioning thumbnail with regards to its viewport
                left += (seekbarRect.left - (videoContainerRect as DOMRect).left);
                // Take into account thumbnail control
                var ctrlWidth = parseInt(window.getComputedStyle(thumbnailElem.current as Element).width);
                if (!isNaN(ctrlWidth)) {
                    left -= ctrlWidth / 2;
                }

                var scale = ((videoContainerRect as DOMRect).height * maxPercentageThumbnailScreen) / thumbnail.height;
                if (scale > maximumScale) {
                    scale = maximumScale;
                }

                // Set thumbnail control position
                if(thumbnailContainer.current) {
                    thumbnailContainer.current.style.left = left + 'px';
                    thumbnailContainer.current.style.display = '';
                    thumbnailContainer.current.style.bottom += Math.round(videoControllerRect.height + 10) + 'px';
                    thumbnailContainer.current.style.height = Math.round(thumbnail.height) + 'px';
                }

                var backgroundStyle = 'url("' + thumbnail.url + '") ' + (thumbnail.x > 0 ? '-' + thumbnail.x : '0') +
                    'px ' + (thumbnail.y > 0 ? '-' + thumbnail.y : '0') + 'px';

                if(thumbnailElem.current) {
                    thumbnailElem.current.style
                    thumbnailElem.current.style.background = backgroundStyle;
                    thumbnailElem.current.style.width = thumbnail.width + 'px';
                    thumbnailElem.current.style.height = thumbnail.height + 'px';
                    thumbnailElem.current.style.transform = 'scale(' + scale + ',' + scale + ')';
                }

                if (thumbnailTimeLabel.current) {
                    thumbnailTimeLabel.current.style.display = 'table';
                    thumbnailTimeLabel.current.textContent = player.convertToTimeCode(mouseTime);
                }
            });
        }
    }

    function hideThumb() {
        if(thumbnailElem.current) {
            thumbnailElem.current.style.background = 'none';
            thumbnailElem.current.style.width = '0px';
            thumbnailElem.current.style.height = '0px';
            thumbnailElem.current.style.transform = 'scale(0)';
        }
        if (thumbnailTimeLabel.current) {
            thumbnailTimeLabel.current.style.display = 'none';
            thumbnailTimeLabel.current.textContent = '';
        }
    }

    //************************************************************************************
    // LOADING
    // ************************************************************************************
    function showLoading() {
        if(iconLoading.current !== null && !firstTimePlay) {
            iconLoading.current.classList.add('icon-loading');
            iconLoading.current.classList.remove('icon-not-loading');
        }
    }


    return (
        <motion.div
            className="TrackPage"
            initial={{width: 0, height: 0, opacity: 0}}
            animate={{width: "100vw", height: "100svh", opacity: 1}}
        >
            <div className="dash-video-player">
                <div className="videoContainer" ref={videoContainer} id="videoContainer">

                    <video preload="auto" width="100vw" height="auto" ref={video} onClick={()=>togglePlayPauseVideoState()}></video>
                    
                    <div id="videoController" ref={videoController} className="video-controller hide">

                        <div id="playPauseBtn" ref={playPauseBtn} onClick={()=>togglePlayPauseVideoState()} className="btn-play-pause" title="Play/Pause">
                            <span id="iconPlayPause" ref={iconPlayPause} className="icon-play"></span>
                        </div>

                        <div id="muteBtn" ref={muteBtn} className="btn-mute control-icon-layout" title="Mute">
                            <span id="iconMute" className="icon-mute-on" onClick={()=>onMuteClick()}></span>
                            <input type="range" id="volumebar" ref={volumebar} onChange={(e)=>setVolume(e)} className="volumebar" 
                                min="0" max="100" step=".01" defaultValue={50}
                            />
                        </div>

                        <span id="videoTime" ref={timeDisplay} className="time-display">00:00:00</span>

                        <div className="seekContainer">
                            <div id="seekbar" ref={seekbar} className="seekbar seekbar-complete"
                                onMouseDown={(e)=>onSeeking(e)}
                                onMouseMove={(e)=>onSeekBarMouseMove(e)}
                                onClick={(e)=>onSeeked(e)}
                                onMouseOut={()=>hideThumb()} 
                                onTouchMove={(e)=>onSeekBarMouseMove(e)}
                                onTouchCancel={(e)=>{onSeeked(e);hideThumb();}}
                                onTouchEnd={(e)=>{onSeeked(e);hideThumb();}}
                            >
                                <div id="seekbar-buffer" ref={seekbarBuffer} className="seekbar seekbar-buffer"></div>
                                <div id="seekbar-play" ref={seekbarPlay} className="seekbar seekbar-play"></div>
                            </div>
                        </div>

                        <span id="videoDuration" ref={durationDisplay} className="duration-display">00:00:00</span>

                        <div id="captionBtn" ref={captionBtn} className="btn-caption" title="Closed Caption">
                            <span className="icon-caption"></span>
                        </div>

                        <div id="fullscreenBtn" ref={fullscreenBtn} onClick={()=>onFullscreenClick()} className="btn-fullscreen" title="Fullscreen">
                            <span className="icon-fullscreen-enter"></span>
                        </div>

                        <div id="thumbnail-container" ref={thumbnailContainer} className="thumbnail-container">
                            <div id="thumbnail-elem" ref={thumbnailElem} className="thumbnail-elem"></div>
                            <div id="thumbnail-time-label" ref={thumbnailTimeLabel} className="thumbnail-time-label"></div>
                        </div>

                    </div>

                    <div id="loading" className='icon-not-loading' ref={iconLoading}>
                        <ClipLoader className='Loader'
                            color="rgb(229,9,20)"
                            size={isSmall?(40):isMedium?(50):isLarge?(60):(70)}
                            aria-label="Loading Spinner"
                            data-testid="loader"
                        />
                    </div>

                    <div id="middlePlayBtn" className='middle-play-btn icon-middle-play-btn-on' 
                        onClick={()=>handleFirstTimePlay()}
                        ref={middlePlayBtn}
                    />

                </div>
            </div>
        </motion.div>
    );
}