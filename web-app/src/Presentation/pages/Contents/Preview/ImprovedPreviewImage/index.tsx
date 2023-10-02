import { useEffect, useState } from "react";

import 'react-loading-skeleton/dist/skeleton.css';

import brokenImage from '@Presentation/assets/img/broken-image.png';

import LoadingSpinner from "@Presentation/components/ImprovedVideo/LoadingSpinner";

type ImprovedImageProps = React.HTMLProps<HTMLImageElement>

export default function ImprovedPreviewImage(props: ImprovedImageProps): JSX.Element {

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    
    useEffect(()=>{
        loadImage();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    function loadImage(){
        const img = new Image();

        img.onload = () => setLoading(false);
        img.onerror = () => setError(true);
        
        img.src = props.src || "";
    }

    if(error)
        return <img 
                    className={props.className}
                    src={brokenImage} 
                    alt="the link of image is broken"
                    style={{filter: "invert(1)"}}
                />

    else if(loading)
        return <LoadingSpinner/>
        
    else
        return <img
                    className={props.className}
                    src={props.src}
                    alt={props.alt}
                    onMouseOver={props.onMouseOver}
                />
                
}