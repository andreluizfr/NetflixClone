import { useEffect, useState } from "react";

import Skeleton, { SkeletonTheme } from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';

import brokenImage from '../../assets/img/broken-image.png';

interface ImprovedImageProps extends React.HTMLProps<HTMLImageElement> {
    hash: string;
}

export default function ImprovedImage(props: ImprovedImageProps): JSX.Element {

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

        //img.crossOrigin = "Anonymous";
        img.src = props.src || "";
    }

    if(error)
        return <img 
                    className={props.className}
                    src={brokenImage} 
                    alt="the link of image is broken"
                    style={{filter: "invert(1)"}}
                    loading="lazy"
                />
    /*
    else if(loading)
        return  <img
                    className={props.className}
                    src={"data:image/png;base64,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"}
                    alt={props.alt}
                    style={{filter: "blur(8px)"}}
                    loading="lazy"
                />
    */
    else if(loading)
        return (
            <SkeletonTheme baseColor="#202020" highlightColor="#444">
                <Skeleton
                    count={1}
                    className={props.className}
                />
            </SkeletonTheme>
        );
        
    else
        return <img
                    className={props.className}
                    src={props.src}
                    alt={props.alt}
                    loading="lazy"
                />
                
}