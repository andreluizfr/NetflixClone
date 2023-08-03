import { useEffect, useState } from "react";

import brokenVideo from '../../assets/img/broken-video.png';

interface ImprovedVideoProps extends React.HTMLProps<HTMLVideoElement> {
    src: string;
    type: string;
    className: string;
}

export default function ImprovedEmbededVideo(props: ImprovedVideoProps): JSX.Element {

    const [error, setError] = useState(false);
    
    useEffect(()=>{
        loadVideo();
    }, []);

    async function loadVideo(){
        const iframeEl = document.createElement('iframe') as HTMLIFrameElement;
        iframeEl.onerror = () => setError(true);
        iframeEl.src = props.src.replace("watch?v=", "embed/") + "?autoplay=1&mute=1&loop=1&controls=0";
    }

    const width = window.innerWidth;

    if(error)
        return <img 
                    className={props.className}
                    src={brokenVideo} 
                    alt="the video is broken"
                    loading="lazy"
                />
 
    else
        return <iframe width={width} height={width/1.7777778}
                    src={props.src.replace("watch?v=", "embed/") + "?autoplay=1&mute=1&loop=1&controls=0"}>
                </iframe>
        
}