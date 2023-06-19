import './styles.css';
import playButton from '../../../assets/svg/play-button.svg';
import infoCircle from '../../../assets/svg/info-circle.svg';
import TheWitcher3Preview from '../../../assets/video/the-witcher-3-preview.mp4';

import { motion } from 'framer-motion';

export default function WorkPreview(): JSX.Element {

    const height = window.innerHeight;

    return (
        <motion.aside 
            className='Work-preview'
            initial={{y: -(height/2), opacity: 0}}
            animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
        >
            <video className='Background-video' muted autoPlay loop>
                <source src={TheWitcher3Preview} type="video/mp4"/>
            </video>
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