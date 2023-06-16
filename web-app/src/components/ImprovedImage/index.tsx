import { useEffect, useRef, useState } from "react";
import { decode } from "blurhash";
import brokenImage from '../../assets/img/broken-image.png';

interface ImprovedImageProps extends React.HTMLProps<HTMLImageElement> {
    hash: string;
    width: number;
    height: number;
    punch?: number;
    resolutionX?: number;
    resolutionY?: number;
    Key?: number | string;
}

export default function ImprovedImage(props: ImprovedImageProps): JSX.Element {

    const ref = useRef<HTMLDivElement>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    const [created, setCreated] = useState(false);
    
    useEffect(()=>{
        loadImage();
        if(!created)
            getBlurImage();
    }, [ref, ref.current]);

    function loadImage(){
        const img = new Image();

        img.onload = () => setLoading(false);
        img.onerror = () => setError(true);

        img.crossOrigin = "anonymous";
        img.src = props.src || "";
    }

    function getBlurImage(){
        const pixels = decode(props.hash, props.width, props.height);
        
        const canvas = document.createElement("canvas");
        canvas.width = props.width;
        canvas.height = props.height;

        const ctx = canvas.getContext("2d");
        if(ctx){
            const imageData = ctx?.createImageData(props.width, props.height);
        
            if(imageData && ctx){
                imageData.data.set(pixels);
                ctx.putImageData(imageData, 0, 0);
            }
            
            if(ref && ref.current){
                ref.current.style.width = props.width.toString()+"px";
                ref.current.style.height = props.height.toString()+"px";
                ref.current.append(canvas);
                setCreated(true);
            }
        }
    }

    if(error)
        return <img 
                    className={props.className}
                    src={brokenImage} 
                    alt="the link of image is broken"
                    width={props.width}
                    height={props.height}
                    key={props.Key}
                    style={{filter: "invert(1)"}}
                    loading="lazy"
                />

    if(loading)
        return  <div 
                    className={props.className}
                    ref={ref} 
                    key={props.Key}
                />
            
    else
        return <img
                    className={props.className}
                    src={props.src}
                    alt={props.alt}
                    width={props.width}
                    height={props.height}
                    key={props.Key}
                    loading="lazy"
                />
                
}