import './styles.scss';

import StreamingPreviewVideoOnHover from '../StreamingPreviewVideoOnHover';

import arrowRightButton from '@Presentation/assets/svg/arrow-right.svg';
import playButton from '@Presentation/assets/svg/play-button.svg';
import likeButton from '@Presentation/assets/svg/like-button.svg';

import { Media, normalizeGenreList } from '@Model/entities/Media';
import { Movie, isMovie } from '@Model/entities/Movie';
import { TvShow, isTvShow } from '@Model/entities/TvShow';
import { Anime, isAnime } from '@Model/entities/Anime';
import { TrackMetadata, formatDuration } from '@Model/entities/Track';

interface props {
    media: Media;
}

export default function MediaCardDialog({media} : props) : JSX.Element {
    
    return (
        <article className="MediaCardDialog">
            <StreamingPreviewVideoOnHover media={media} trackMetadata={{id: "00000000-0000-0000-0000-000000000000"} as unknown as TrackMetadata}/>

            <div className='Preview-menu'>
                <div className='Toolbar'>
                    <button className='Button'>
                        <img src={playButton} alt="play icon"/>
                        Assistir 
                    </button>

                    <div className='Circle-1'>
                        <span className='Add-to-list-button' >+</span>
                    </div>

                    <span className='Circle-2'>
                        <img className='Like-button' src={likeButton}/>
                    </span>
                </div>

                <div className='Circle-3'>
                    <img className='Sound-button' src={arrowRightButton}/>
                </div>
            </div>

            <div className='Blured-border'/>

            <div className='Informations'>

                <div className='Column-1'>
                    <div className='Row-1'>
                        <div className='Relevance'>
                            98% relevante
                        </div>

                        {isMovie(media) &&
                            <div className='Year'>{(media as Movie).releaseYear}</div>
                        }
                        {isTvShow(media) &&
                            <div className='Number-episodes'>{(media as TvShow).episodeTracks?.length} episódios</div>
                        }
                        {isAnime(media) &&
                            <div className='Number-episodes'>{(media as Anime).episodeTracks?.length} episódios</div>
                        }

                        {isMovie(media) &&
                            <div className='Duration'>{formatDuration((media as Movie).episodeTrack?.duration)}</div>
                        }

                        <div className='Hd'>HD</div>
                    </div>

                    <div className='Row-2'>
                    {
                        (media.ageRating === 10) &&
                        <div className='Age-rating Age-rating-10'>
                            {media.ageRating} 
                        </div>
                    }
                    {
                        (media.ageRating === 12) &&
                        <div className='Age-rating Age-rating-12'>
                            {media.ageRating} 
                        </div>
                    }
                    {
                        (media.ageRating === 14) &&
                        <div className='Age-rating Age-rating-14'>
                            {media.ageRating} 
                        </div>
                    }
                    {
                        (media.ageRating === 16) &&
                        <div className='Age-rating Age-rating-16'>
                            {media.ageRating} 
                        </div>
                    }
                    {
                        (media.ageRating === 18) &&
                        <div className='Age-rating Age-rating-18'>
                            {media.ageRating} 
                        </div>
                    }
                    </div>

                    <div className='Row-3'>
                        <div className='Synopsis'>
                            {media.descriptions}
                        </div>
                    </div>
                </div>

                <div className='Column-2'>
                    {isMovie(media) &&
                        <div className='Label-tags'>
                            Elenco:
                            <span className='Cast'> {(media as Movie).actorsActresses.join(", ")}</span>
                        </div>
                    }
                    {isTvShow(media) &&
                        <div className='Label-tags'>
                            Elenco:
                            <span className='Cast'> {(media as TvShow).actorsActresses.join(", ")} episódios</span>
                        </div>
                    }
                    {isAnime(media) &&
                        <div className='Label-tags'>
                            Elenco:
                            <span className='Cast'> {(media as Anime).voiceActorsActresses.join(", ")} episódios</span>
                        </div>
                    }
                    <div className='Label-tags'>
                        Gêneros:
                        <span className='Genres'> {normalizeGenreList(media.genres).join(", ")}</span>
                    </div>
                </div>

            </div>
            
            {(isTvShow(media) || isAnime(media)) &&
                <div className='Episodes-container'>
                    <div className='Title'>

                    </div>
                </div>
            }

        </article>
    )
}