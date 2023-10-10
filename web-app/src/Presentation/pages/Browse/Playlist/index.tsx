import './styles.css';

import MediaCard from './MediaCard';

import { Media } from '@Model/entities/Media';

import { motion } from 'framer-motion';
import { useRef, useState } from 'react';

interface props {
    title: string,
    medias: Media[]
}

export default function Playlist({title, medias}: props) {

    //  ############# Manipulação de dados na view ##################
    const playlistContainerRef = useRef(null);

    const [intervals, setIntervals] = useState<NodeJS.Timer>();

    function moveContainer(direction: string) {

        const interval = setInterval(()=>{

            if(playlistContainerRef.current){

                if(direction === "left") (playlistContainerRef.current as HTMLDivElement).scrollBy(-2, 0);

                else if(direction === "right") (playlistContainerRef.current as HTMLDivElement).scrollBy(2, 0);
            }
                
        }, 1);

        setIntervals(interval);
    }


    //  ############# Renderização do conteúdo ##################
    const width = window.innerWidth;

    return (
        <section className='Section'>
            <h1 className='Title'>
                {title}
            </h1>

            <div className='Playlist-container'>
                <span 
                    className='Arrow Left-arrow'
                    onMouseOver={()=>moveContainer("left")}
                    onMouseOut={()=>clearInterval(intervals)}
                />
            
                <motion.div 
                    className='Playlist'
                    initial={{x: -(width/2), opacity: 0}}
                    animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    ref={playlistContainerRef}
                >
                    {   
                        medias?.map(media => <MediaCard media={media} />)
                    }
                </motion.div>

                <span 
                    className='Arrow Right-arrow'
                    onMouseOver={()=>moveContainer("right")}
                    onMouseOut={()=>clearInterval(intervals)}
                />
            </div>
        </section>
    );
}