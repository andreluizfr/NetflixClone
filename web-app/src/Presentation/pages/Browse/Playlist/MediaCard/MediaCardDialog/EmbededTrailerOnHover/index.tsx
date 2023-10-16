import "./styles.css";

import brokenVideo from '@Presentation/assets/img/broken-video.png';

import { useEffect, useState } from "react";

interface EmbededTrailerOnHoverProps extends React.HTMLProps<HTMLVideoElement> {
    src: string;
    loadingImage: string;
}

export default function EmbededTrailerOnHover(props: EmbededTrailerOnHoverProps): JSX.Element {

    const [error, setError] = useState(false);
    
    useEffect(()=>{
        loadVideo();
    }, []);

    async function loadVideo(){
        const iframeEl = document.createElement('iframe') as HTMLIFrameElement;
        iframeEl.onerror = () => setError(true);
        iframeEl.src = props.src.replace("watch?v=", "embed/") + "?autoplay=1&mute=1&loop=1&controls=0";
    }

    if(error)
        return <img 
                    className="EmbededTrailerOnHover"
                    src={brokenVideo} 
                    alt="the video is broken"
                    loading="lazy"
                />
 
    else
        return <iframe width={860} height={483}
                    src={props.src.replace("watch?v=", "embed/") + "?autoplay=1&mute=1&loop=1&controls=0"}>
                </iframe>
        
}