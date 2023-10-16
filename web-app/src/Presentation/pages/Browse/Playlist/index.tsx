import './styles.css';

import MediaCard from './MediaCard';

import { Media } from '@Model/entities/Media';

import { motion } from 'framer-motion';
import { useEffect, useRef, useState } from 'react';

interface props {
    title: string,
    medias: Media[]
}

export default function Playlist({title, medias}: props) {

    //  ############# Manipulação de dados na view ##################
    const playlistContainerRef = useRef(null);

    const width = window.innerWidth;

    const [translateX, setTranslateX] = useState(0);
    const [translateXStr, setTranslateXStr] = useState("0%");

    const mediasSize = medias.length;
    const [copyMedias, setCopyMedias] = useState(medias);

    function moveContainer(direction: string) {
        console.log(direction + " - " + translateX);
        if(playlistContainerRef.current){
            if(direction === "left" && translateX < 0) {
                setTranslateX(prev=>prev+=100);
                copyMedias.splice(copyMedias.length - mediasSize, mediasSize);
            }
            else if(direction === "right"){ 
                setTranslateX(prev=>prev-=100);
                setCopyMedias([...copyMedias, ...medias]);
            }
        }
    }

    useEffect(()=>{
        setTranslateXStr(translateX+"%");
    }, [translateX]);


    //  ############# Renderização do conteúdo ##################
    return (
        <section className='Section'>
            <h1 className='Title'>
                {title}
            </h1>

            <div className='Playlist-container'>

                <div className={(translateX===0)?'Arrow-background-left Hidden':'Arrow-background-left'}>
                    <span 
                        className='Arrow Left-arrow'
                        onClick={()=>moveContainer("left")}
                    />
                </div>
            
                <motion.div 
                    className='Playlist'
                    initial={{x: -(width/2), opacity: 0}}
                    animate={{x: 0, opacity: 1, translateX: translateXStr, transition:{type: "spring", bounce: 0.1, duration: 1.5}}}
                    ref={playlistContainerRef}
                >
                    {   
                        copyMedias?.map(media => <MediaCard media={media} />)
                    }
                </motion.div>
                
                <div className='Arrow-background-right'>
                    <span 
                        className='Arrow Right-arrow'
                        onClick={()=>moveContainer("right")}
                    />
                </div>
            </div>
        </section>
    );
}