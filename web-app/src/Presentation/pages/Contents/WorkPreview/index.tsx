import './styles.css';
import playButton from '../../../assets/svg/play-button.svg';
import infoCircle from '../../../assets/svg/info-circle.svg';

import { Suspense, lazy } from 'react';

//import ImprovedVideo from '@Presentation/components/ImprovedVideo';
const ImprovedVideo = lazy(() => import('@Presentation/components/ImprovedVideo'));

import { motion } from 'framer-motion';


import LoadingSpinner from '@Presentation/components/ImprovedVideo/LoadingSpinner';

export default function WorkPreview(): JSX.Element {

    const height = window.innerHeight;

    return (
        <motion.aside 
            className='Work-preview'
            initial={{y: -(height/2), opacity: 0}}
            animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
        >
            <Suspense fallback={<LoadingSpinner/>}>
                <ImprovedVideo src='/the-witcher-3-preview.mp4' type='video/mp4' className='Background-video'/>
            </Suspense>
        
            <div className='Work-preview-menu'>
                <h1 className='Title'>
                    The Witcher: temporada 3
                </h1>
                <div className='Toolbar'>
                    <button className='Button'>
                        <img src={playButton} alt="play icon"/>
                        Assistir 
                    </button>

                    <button className='Button'>
                        <img src={infoCircle} alt="info circle icon"/>
                        Mais informações
                    </button>
                </div>
            </div>
        </motion.aside>
    )
}