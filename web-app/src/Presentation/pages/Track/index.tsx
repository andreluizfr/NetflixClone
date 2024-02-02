import TrackPage from './TrackPage';
//import Loading from '../Loading';

import { Track } from '@Model/entities/Track';
import { Media } from '@Model/entities/Media';

//import { GetTrackService } from '@Services/GetTrack/GetTrackService';
//import { GetMediaService } from '@Services/GetMedia/GetMediaService';

import { useSearchParams } from 'react-router-dom';

export default function TrackPageProvider () {

    const [searchParams] = useSearchParams();
    const trackId = searchParams.get("trackId");
    const mediaId = searchParams.get("mediaId");
    
    console.log("mediaId=", mediaId,",trackId=", trackId);

    //const getMediaServiceResult = GetMediaService(mediaId);
    //const getTrackServiceResult = GetTrackService(trackId);

    const media = {} as Media;
    const track = {} as Track;

    //if(getMediaServiceResult.data?.data && getTrackServiceResult.data?.data)
        return (
            <div>
                {/*<TrackPage media={getMediaServiceResult.data.data} track={getTrackServiceResult.data.data}/>*/}
                <TrackPage media={media} track={track}/>
            </div>
        ); 
    // else
    //     return <Loading/>
}