import './styles.css';
import ImprovedImage from '../../../components/ImprovedImage';

import { motion, motionValue } from 'framer-motion';
import { useRef } from 'react';

interface props {
    title: string,
    works: {
        id: string, 
        thumbnailUrl: string, 
        hash: string
    }[],
    
}

const PADDING_X = 4;
const IMG_WIDTH = 192;

export default function Playlist({title, works}: props) {

    const width = window.innerWidth;

    let isMouseDown = false;
    let startX = 0;

    const baseX = motionValue(0);
    const translateX = motionValue(0);
    const walk = motionValue(0);

    const playlistContainerRef = useRef(null);

    function handleMouseDown(e: React.MouseEvent<HTMLDivElement>){
        e.preventDefault();

        if(e.buttons){
            isMouseDown = true;
            startX = (e.pageX);

            if (playlistContainerRef?.current)
                (playlistContainerRef.current as HTMLDivElement).style.cursor = 'grabbing'; /* Altera o cursor do mouse para "mãozinha fechada" ao segurar o item */
        }
    }

    function handleMouseMove(e: React.MouseEvent<HTMLDivElement>){
        e.preventDefault();

        if (e.buttons === 1 && isMouseDown) {
            walk.set(e.pageX - startX);
            translateX.set(baseX.get() + walk.get());
        }
    }
      
    function handleMouseUp(e: React.MouseEvent<HTMLDivElement>) {
        e.preventDefault();

        //se for solto estando com valor positivo, quer dizer que ta sobrando espaço do lado esquerdo e tem que voltar para o estado inicial
        if(translateX.get() > 0) {
            translateX.set(0);
            baseX.set(0);
        }
        else if(translateX.get() < (- (IMG_WIDTH * works.length) + (PADDING_X * works.length) + width)){  //
            translateX.set(- (IMG_WIDTH * works.length) + (PADDING_X * works.length) + width);
            baseX.set(- (IMG_WIDTH * works.length) + (PADDING_X * works.length) + width);
        }
        else 
            baseX.set(translateX.get());

        isMouseDown = true;

        if (playlistContainerRef?.current)
            (playlistContainerRef.current as HTMLDivElement).style.cursor = 'grab'; /* Altera o cursor do mouse para "mãozinha" ao soltar o item */
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
                style={{x: translateX, transition: "transform 50ms"}}
                onMouseDown={(e)=>handleMouseDown(e)}
                onMouseMove={(e)=>handleMouseMove(e)}
                onMouseUp={(e)=>handleMouseUp(e)}
                ref={playlistContainerRef}
            >
                {   
                    works.map((work)=>
                        <ImprovedImage
                            src={work.thumbnailUrl} 
                            className='Thumbnail'
                            key={"work-"+work.id}
                            hash={work.hash}
                            width={192}
                            height={108}
                        />
                    )
                }
            </motion.div>
        </section>
    );
}