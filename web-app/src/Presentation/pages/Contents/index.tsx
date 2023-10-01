import './styles.css';
import logo from '../../assets/svg/logo.svg';

import { useRef, useEffect, useState, memo } from 'react';

import Header from './Header';
import Preview from './Preview';
import Playlist from './Playlist';
import Footer from './Footer';

import { GetCurrentPreviewMediaService } from '@Application/useCases/GetCurrentPreviewMedia/GetCurrentPreviewMediaService';
import { GetMediaListsService } from '@Application/useCases/GetMediaLists/GetMediaListsService';

import { MediaList } from '@Model/entities/MediaList';
import { Media } from '@Model/entities/Media';

import { Helmet } from 'react-helmet-async';

import { motion, useScroll, useTransform  } from 'framer-motion';

import { StoreState } from '@Infrastructure/stores/redux/config';
import { removeUser } from '@Infrastructure/stores/redux/features/userSlice';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function ContentsPage(): JSX.Element {

    //  ############# Redirecionamento de página ##################
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const user = useSelector((state: StoreState) => state.user);

    useEffect(()=>{
        
        if(user.data && !user.data.account.active){
            toast.error("Ainda não identificamos o seu pagamento", {
                position: "top-center",
                hideProgressBar: true
            });
            dispatch(removeUser());
            setTimeout(()=>navigate("/signup/planform"), 2000);
        }
        
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [user.data]);


    //  ############# Manipulação de requisição ##################
    const [mediaLists, setMediaLists] = useState<MediaList[]>([] as MediaList[]);

    const [previewMedia, setPreviewMedia] = useState<Media | null>(null);

    const getCurrentPreviewMediaResult = GetCurrentPreviewMediaService();

    const getMediaListsResult = GetMediaListsService();

    useEffect(()=>{
        if(getCurrentPreviewMediaResult.isError && getCurrentPreviewMediaResult.error) 
            toast.error("Parece que o servidor está enfrentando problemas para buscar o preview inicial.", {
                position: "bottom-center",
                hideProgressBar: true
            });
        else if(getCurrentPreviewMediaResult.data?.data) 
            setPreviewMedia(getCurrentPreviewMediaResult.data.data.media);

    }, [getCurrentPreviewMediaResult.isError, getCurrentPreviewMediaResult.error, getCurrentPreviewMediaResult.data]);

    useEffect(()=>{
        if(getMediaListsResult.isError && getMediaListsResult.error) 
            toast.error("Parece que o servidor está enfrentando problemas para buscar as mídias.", {
                position: "bottom-center",
                hideProgressBar: true
            });
        else if(getMediaListsResult.data?.data) 
            setMediaLists(getMediaListsResult.data.data);

    }, [getMediaListsResult.isError, getMediaListsResult.error, getMediaListsResult.data]);

    
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

    const [isInitialPreview, setIsInitialPreview]  = useState(true);
    

    //  ############# Renderização do conteúdo ##################
    const MemoizedFooter = memo(Footer);

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

             <ToastContainer
                autoClose={5000}
                newestOnTop={true}
                pauseOnFocusLoss={false}
                draggable={false}
                pauseOnHover={false}
                hideProgressBar={false}
                theme="dark"
            />

            <Header headerRef={headerRef}/>

            <Preview media={previewMedia} isInitialPreview={isInitialPreview}/>
            
            <main className='Contents-container'>
                {/*<Playlist title={"Continuar assistindo como perfil1"} medias={medias}/>*/}
                {mediaLists.map((mediaList, index)=>{
                    return <Playlist 
                                title={mediaList.title} 
                                medias={mediaList.medias} 
                                key={mediaList.title+index} 
                                setPreviewMedia={setPreviewMedia}
                                setIsInitialPreview={setIsInitialPreview}
                            />
                })}
            </main>

            <MemoizedFooter/>

        </motion.div>  
    )
}