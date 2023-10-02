import './styles.css';
import logo from '../../assets/svg/logo.svg';

import { useRef, memo } from 'react';

import { GetMediaService } from '@Application/useCases/GetMedia/GetMediaService';

import Header from '@Presentation/components/HeaderLogged';
import Footer from '@Presentation/components/FooterLogged';
import { Helmet } from 'react-helmet-async';

import { motion, useScroll, useTransform  } from 'framer-motion';

import { useParams } from 'react-router-dom';


export default function MediaPage(): JSX.Element {

    //  ############# Manipulação de dados da view ##################
    const pageRef = useRef(null);
    const headerRef = useRef(null);

    const { scrollYProgress } = useScroll({
		target: pageRef
	});
    
    useTransform(scrollYProgress, value=>{
        //value in %
        if(headerRef?.current && (value*100) >= 8)
            (headerRef.current as HTMLElement).style.backgroundColor = "#000000";
        else if(headerRef?.current && (value*100) < 8)
            (headerRef.current as HTMLElement).style.backgroundColor = "transparent";
	});

    
    //  ############# Manipulação de requisição ##################
    const { mediaId } = useParams();

    const getMediaResult = GetMediaService(mediaId);

    //  ############# Renderização do conteúdo ##################
    const MemoizedFooter = memo(Footer);

    return (
        <motion.div className='MediaPage' ref={pageRef}>
             <Helmet>
                <link rel="preconnect" href="https://fonts.googleapis.com"/>
                <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous"/>
                <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet"/>
                <meta property="og:title" content="Netflix Contents" />
                <meta property="og:url" content="http://localhost:5173/media" />
                <meta property="og:image" content={logo} />
                <meta property="og:image:alt" content="Netflix logo" />
                <meta property="og:description" content="Watch the best Movies, Tv Series and Animes" />
                <meta property="og:site_name" content="Netflix" />
            </Helmet>

            <Header headerRef={headerRef}/>

            <main className='Media-container'>

            </main>

            <MemoizedFooter/>
        </motion.div>
    );
}