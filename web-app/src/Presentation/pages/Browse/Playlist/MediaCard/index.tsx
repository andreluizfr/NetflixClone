import './styles.css';

import EmbededTrailerOnHover from './EmbededTrailerOnHover';
import MediaCardDialog from './MediaCardDialog';

import arrowRightButton from '@Presentation/assets/svg/arrow-right.svg';
import playButton from '@Presentation/assets/svg/play-button.svg';
import likeButton from '@Presentation/assets/svg/like-button.svg';

import { Media, normalizeGenreList } from '@Model/entities/Media';
import { Movie, isMovie } from '@Model/entities/Movie';
import { TvShow, isTvShow } from '@Model/entities/TvShow';
import { Anime, isAnime } from '@Model/entities/Anime';

import ImprovedImage from '@Presentation/components/ImprovedImage';

import { memo, useState } from 'react';

import * as Dialog from '@radix-ui/react-dialog';

interface props {
    media: Media | Movie | TvShow | Anime;
}

export default function MediaCard ({media}: props) : JSX.Element {

    const [isHovered, setIsHovered] = useState(false);
    const [showMediaCardDialog, setShowMediaCardDialog] = useState(false);

    const MemoizedMediaCardDialog = memo(MediaCardDialog);

    return(
        //só aparecer depois do tempo da conclusão da transição por parte do css 
        <article className='MediaCard' onMouseOver={()=>setIsHovered(true)} onMouseOut={()=>setIsHovered(false)}>

            {
                isHovered?
                    <EmbededTrailerOnHover src={media.trailerUrl} loadingImage={media.thumbnailUrl}/>
                    :
                    <ImprovedImage
                        src={media.thumbnailUrl} 
                        className='Thumbnail'
                        key={"media-"+media.id+"-"+Math.floor(Math.random() * 1000000)}
                    />
            }

            <div className='Mini-menu Hover-visible'>
                <div className='Toolbar'>
                    <div className='Circle-invert'>
                        <img className='Play-button' src={playButton}/>
                    </div>
                    <div className='Circle-1'>
                        <span className='Add-to-list-button' >+</span>
                    </div>
                    <span className='Circle-2'>
                        <img className='Like-button' src={likeButton}/>
                    </span>
                </div>

                <Dialog.Root>
                    <Dialog.Trigger asChild>
                        <div className='Circle-3'>
                            <img className='More-infos-button' src={arrowRightButton}/>
                        </div>
                    </Dialog.Trigger>
                    <Dialog.Portal>
                        <Dialog.Overlay className="DialogOverlay" />
                        <Dialog.Content className="DialogContent">
                            <MemoizedMediaCardDialog media={media} setShowMediaCardDialog={setShowMediaCardDialog}/>
                        </Dialog.Content>
                    </Dialog.Portal>
                </Dialog.Root>
            </div>

            <div className='Informations Hover-visible'>
                <div className='Relevance'>
                    98% relevante
                </div>
                {
                    (media.ageRating === 10) &&
                    <div className='Age-rating-10'>
                        {media.ageRating} 
                    </div>
                }
                {
                    (media.ageRating === 12) &&
                    <div className='Age-rating-12'>
                        {media.ageRating} 
                    </div>
                }
                {
                    (media.ageRating === 14) &&
                    <div className='Age-rating-14'>
                        {media.ageRating} 
                    </div>
                }
                {
                    (media.ageRating === 16) &&
                    <div className='Age-rating-16'>
                        {media.ageRating} 
                    </div>
                }
                {
                    (media.ageRating === 18) &&
                    <div className='Age-rating-18'>
                        {media.ageRating} 
                    </div>
                }
                {isMovie(media) &&
                    <div className='Year'>{(media as Movie).releaseYear}</div>
                }
                {isTvShow(media) &&
                    <div className='Number-episodes'>{(media as TvShow).tracks?.length} episódios</div>
                }
                {isAnime(media) &&
                    <div className='Number-episodes'>{(media as Anime).tracks?.length} episódios</div>
                }
                <div className='Hd'>HD</div>
            </div>

            <div className='Genres Hover-visible'>
                {normalizeGenreList(media.genres).join("  ⚬  ")}
            </div>
            
            {showMediaCardDialog &&
                <MemoizedMediaCardDialog media={media} setShowMediaCardDialog={setShowMediaCardDialog}/>
            }
        </article>
    );
}