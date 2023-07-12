import { useEffect, useState } from "react";

import brokenVideo from '../../assets/img/broken-video.png';

import LoadingSpinner from "./LoadingSpinner";

interface ImprovedVideoProps extends React.HTMLProps<HTMLVideoElement> {
    src: string;
    type: string;
    className: string;
}

export default function ImprovedVideo(props: ImprovedVideoProps): JSX.Element {

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    
    useEffect(()=>{
        loadVideo();
    }, []);

    async function loadVideo(){
        
        const videoEl = document.createElement('video') as HTMLVideoElement;
        videoEl.oncanplay = () => setLoading(false);
        videoEl.onerror = () => setError(true);
        videoEl.preload = "auto";

        const sourceEl = document.createElement('source') as HTMLSourceElement;
        sourceEl.src = import.meta.env.VITE_APP_BASE_URL + props.src;
        sourceEl.type = props.type;
        
        videoEl.appendChild(sourceEl);
    }

    if(error)
        return <img 
                    className={props.className}
                    src={brokenVideo} 
                    alt="the video is broken"
                    loading="lazy"
                />

    else if(loading)
        return <LoadingSpinner/>
            
    else
        return <video 
                    className={props.className}
                    preload="none" 
                    muted 
                    autoPlay 
                    loop
                >
                    <source src={props.src} type="video/mp4"/>
                </video>
                
}