import './styles.css';
import playButton from '@Presentation/assets/svg/play-button.svg';
import infoCircle from '@Presentation/assets/svg/info-circle-2.svg';

import { GetCurrentPreviewMediaService } from '@Application/useCases/GetCurrentPreviewMedia/GetCurrentPreviewMediaService';

import ImprovedEmbededVideo from '@Presentation/components/ImprovedEmbededVideo';
import LoadingSpinner from '@Presentation/components/ImprovedVideo/LoadingSpinner';

import { Media } from '@Model/entities/Media';

import { motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import React from 'react';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface PreviewProps extends React.HTMLAttributes<HTMLElement>{
}

export default function Preview({}: PreviewProps): JSX.Element {

    //  ############# Manipulação de requisição ##################
    const [previewMedia, setPreviewMedia] = useState<Media | null>(null);

    const getCurrentPreviewMediaResult = GetCurrentPreviewMediaService();

    useEffect(()=>{
        if(getCurrentPreviewMediaResult.isError && getCurrentPreviewMediaResult.error) 
            toast.error("Parece que o servidor está enfrentando problemas para buscar o preview inicial.", {
                position: "bottom-center",
                hideProgressBar: true
            });
        else if(getCurrentPreviewMediaResult.data?.data) 
            setPreviewMedia(getCurrentPreviewMediaResult.data.data.media);

    }, [getCurrentPreviewMediaResult.isError, getCurrentPreviewMediaResult.error, getCurrentPreviewMediaResult.data]);


    //  ############# Manipulação de dados da view #################
    

    //  ############# Renderização do conteúdo ##################
    const height = window.innerHeight;

    if(previewMedia)
        return(
            <motion.aside 
                className='Preview'
                initial={{y: -(height/2), opacity: 0}}
                animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
            >   
                <ToastContainer
                    autoClose={5000}
                    newestOnTop={true}
                    pauseOnFocusLoss={false}
                    draggable={false}
                    pauseOnHover={false}
                    hideProgressBar={false}
                    theme="dark"
                />

                <ImprovedEmbededVideo src={previewMedia.trailerUrl} type='video/mp4' className='Background-video'/>
        
                <div className='Preview-infos'>

                    <h1 className='Title'>
                        {previewMedia.title}
                    </h1>

                    <p className='Description'>
                        {previewMedia.descriptions}
                    </p>

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

                <div className='Blured-border'/>

            </motion.aside>
        );

    return (
        <motion.aside 
            className='Preview'
            initial={{y: -(height/2), opacity: 0}}
            animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
        >
            <LoadingSpinner/>
        </motion.aside>
    );
}