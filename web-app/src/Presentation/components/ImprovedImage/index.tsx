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
                    onMouseOver={props.onMouseOver}
                />
                
}