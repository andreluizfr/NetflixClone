import './styles.css';
import logo from '../../assets/svg/logo.svg';

import { useRef, lazy, Suspense, useEffect, useState } from 'react';

import Header from './Header';
import LoadingPage from '../../pages/Loading';
const WorkPreview = lazy(()=> import('./WorkPreview'));
import Playlist from './Playlist';
import Footer from './Footer';

import playlistsData from './playlists.json';

import { makeHttpClient } from '@Main/factories/infrastructure/makeHttpClient';

import { Helmet } from 'react-helmet-async';

import { motion, useScroll, useTransform  } from 'framer-motion';
import { Media } from '@Model/entities/Media';


const playlists = playlistsData.playlists;

export default function ContentsPage(): JSX.Element {
    const pageRef = useRef(null);
    const headerRef = useRef(null);

	const { scrollYProgress } = useScroll({
		target: pageRef
	});
    
    useTransform(scrollYProgress, value=>{
        //value in %
        if(headerRef?.current && (value*100) >= 10)
            (headerRef.current as HTMLElement).style.backgroundColor = "#000000";
        else if(headerRef?.current && (value*100) < 10)
            (headerRef.current as HTMLElement).style.backgroundColor = "transparent";
	});

    const [movies, setMovies] = useState<Media[]>([] as Media[]);

    const httpClient = makeHttpClient<Media[]>();
    
    useEffect(()=>{
        httpClient.get("/movie/getAll").then(response=>{
            setMovies(response.data);
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

            <Suspense fallback={<LoadingPage/>}>
                <WorkPreview/>
            </Suspense>
            
            <main className='Contents-container'>
                <Playlist title={"Continuar assistindo como perfil1"} medias={movies}/>
                {playlists.map((playlist, index)=>{
                    return <Playlist title={playlist.title} medias={movies} key={playlist.title+index}/>
                })}
            </main>

            <Footer/>

        </motion.div>  
    )
}