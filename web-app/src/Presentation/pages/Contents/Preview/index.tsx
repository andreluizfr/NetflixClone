import './styles.css';
import playButton from '../../../assets/svg/play-button.svg';
import infoCircle from '../../../assets/svg/info-circle.svg';
import ImprovedPreviewImage from './ImprovedPreviewImage';
import ImprovedEmbededVideo from '@Presentation/components/ImprovedEmbededVideo';
import LoadingSpinner from '@Presentation/components/ImprovedVideo/LoadingSpinner';

import { Media } from '@Model/entities/Media';
import { Movie, isMovie } from '@Model/entities/Movie';
import { TvShow, isTvShow } from '@Model/entities/TvShow';
import { Anime, isAnime } from '@Model/entities/Anime';

import { motion } from 'framer-motion';
import { useEffect } from 'react';

interface PreviewProps extends React.HTMLAttributes<HTMLElement>{
    media: Media | null;
    isInitialPreview: boolean;
}

export default function Preview({media, isInitialPreview}: PreviewProps): JSX.Element {

    //  ############# Renderização do conteúdo ##################
    const height = window.innerHeight;

    useEffect(()=>{
        const pf = document.getElementsByClassName("Preview-infos")[0] as HTMLDivElement;
        pf?.setAttribute("isInitialPreview", "true");
    }, []);

    useEffect(()=>{
        const pf = document.getElementsByClassName("Preview-infos")[0] as HTMLDivElement | undefined;
        pf?.setAttribute("isInitialPreview", JSON.stringify(isInitialPreview));
    }, [isInitialPreview]);

    if(media)
        return(
            <motion.aside 
                className='Preview'
                initial={{y: -(height/2), opacity: 0}}
                animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
            >   
                {isInitialPreview?
                    <ImprovedEmbededVideo src={media.trailerUrl} type='video/mp4' className='Background-video'/>
                    :
                    <ImprovedPreviewImage src={media.posterUrl} className='Background-image'/>
                }
        
                <div className='Preview-infos'>

                    <h1 className='Title'>
                        {media.title}
                    </h1>

                    {!isInitialPreview &&
                        <>
                        <div className="Row-1">
                            <span className='Relevance'>100% relevante</span>
                            <span>{media.releaseYear}</span>
                            {(isMovie(media) && (media as Movie).isMovieSeries) &&
                                <span>Filme {(media as Movie).sequenceNumber}</span>
                            }
                            {isTvShow(media) &&
                                <span>{(media as TvShow).numberOfSeasons} Temporadas</span>
                            }
                            {isAnime(media) &&
                                <span>{(media as Anime).numberOfSeasons} Temporadas</span>
                            }
                        </div>

                        <div className="Row-2">
                            <p className='Description'>
                                {media.descriptions}
                            </p>
                        </div>

                        <div className="Row-3">
                            {isMovie(media) &&
                                <i className='ActorsActresses'>{(media as Movie).actorsActresses.join(", ")}</i>
                            }
                            {isTvShow(media) &&
                                <i className='ActorsActresses'>{(media as TvShow).actorsActresses.join(", ")}</i>
                            }
                            <i className='Genres'>{media.genres.join(", ")}</i>
                        </div>
                        </>
                    }
                    
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