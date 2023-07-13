import './styles.css';
import logo from '../../assets/svg/logo.svg';

import { useRef, useEffect, useState } from 'react';

import Header from './Header';
import Preview from './Preview';
import Playlist from './Playlist';
import Footer from './Footer';

import { makeHttpClient } from '@Main/factories/infrastructure/makeHttpClient';
import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';

import { MediaList } from '@Model/entities/MediaList';
import { Media } from '@Model/entities/Media';

import { Helmet } from 'react-helmet-async';

import { motion, useScroll, useTransform  } from 'framer-motion';

export default function ContentsPage(): JSX.Element {

    const persistentStorage = makePersistentStorage();

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

    const [mediasLists, setMediasLists] = useState<MediaList[]>([] as MediaList[]);

    const httpClient = makeHttpClient<MediaList[]>();

    const [previewMedia, setPreviewMedia] = useState<Media | null>(null);
    
    useEffect(()=>{
        
        const accessToken = persistentStorage.get("x-access-token");

        httpClient.get(
            "/mediaList/getAll",
            {headers: { Authorization: `Bearer ${accessToken}` }}
        ).then(response=>{
            console.log(response);
            if(response.data)
                setMediasLists(response.data);
        });
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <motion.div className='ContentsPage' ref={pageRef}>
            <Helmet>
                <link rel="preconnect" href="https://fonts.googleapis.com"/>
                <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous"/>
                <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet"/>
    <meta property="og:title" content="Netflix Contents" />
                <meta property="og:url" content="http://localhost:5173/contents" />
                <meta property="og:image" content={logo} />
                <meta property="og:image:alt" content="Netflix logo" />
                <meta property="og:description" content="Watch the best Movies, Tv Series and Animes" />
                <meta property="og:site_name" content="Netflix" />
            </Helmet>

            <Header headerRef={headerRef}/>

            <Preview media={previewMedia}/>
            
            <main className='Contents-container'>
                {/*<Playlist title={"Continuar assistindo como perfil1"} medias={medias}/>*/}
                {mediasLists.map((mediasList, index)=>{
                    return <Playlist title={mediasList.title} medias={mediasList.medias} key={mediasList.title+index} setPreviewMedia={setPreviewMedia}/>
                })}
            </main>

            <Footer/>

        </motion.div>  
    )
}