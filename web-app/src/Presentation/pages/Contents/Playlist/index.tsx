import './styles.css';
import ImprovedImage from '../../../components/ImprovedImage';

import { motion } from 'framer-motion';
import { useRef } from 'react';
import { Media } from '@Model/entities/Media';

interface props {
    title: string,
    medias: Media[],
}

export default function Playlist({title, medias}: props) {

    const width = window.innerWidth;

    let isMouseDown = false;
    let previousX = 0;

    const playlistContainerRef = useRef(null);

    function handleMouseDown(e: React.MouseEvent<HTMLDivElement>){
        e.preventDefault();

        if(e.buttons === 1){
            isMouseDown = true;
            previousX = e.pageX

            if (playlistContainerRef?.current)
                (playlistContainerRef.current as HTMLDivElement).style.cursor = 'grabbing'; /* Altera o cursor do mouse para "mãozinha fechada" ao segurar o item */
        }
    }

    function handleMouseMove(e: React.MouseEvent<HTMLDivElement>){
        e.preventDefault();

        if (e.buttons === 1 && isMouseDown) {

            const currentX = e.pageX;
            if(currentX  > previousX && playlistContainerRef?.current)
                    (playlistContainerRef.current as HTMLDivElement).scrollBy(-2, 0); //2 factor

            if(currentX  < previousX && playlistContainerRef?.current)
                (playlistContainerRef.current as HTMLDivElement).scrollBy(2, 0); //2 factor

            previousX = currentX ;
        }

    }
      
    function handleMouseUp(e: React.MouseEvent<HTMLDivElement>) {
        e.preventDefault();

        isMouseDown = true;

        if (playlistContainerRef?.current){
            (playlistContainerRef.current as HTMLDivElement).style.cursor = 'grab'; /* Altera o cursor do mouse para "mãozinha" ao soltar o item */
        }
    }

    return (
        <section className='Section'>
            <h1 className='Title'>
                {title}
            </h1>

            <motion.div 
                className='Playlist'
                initial={{x: -(width/2), opacity: 0}}
                animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                onMouseDown={(e)=>handleMouseDown(e)}
                onMouseMove={(e)=>handleMouseMove(e)}
                onMouseUp={(e)=>handleMouseUp(e)}
                ref={playlistContainerRef}
            >
                {   
                    medias.map((media)=>
                        <ImprovedImage
                            src={media.thumbnailUrl} 
                            className='Thumbnail'
                            key={"media-"+media.id}
                            hash={media.thumbnailBlurHash}
                        />
                    )
                }
            </motion.div>
        </section>
    );
}