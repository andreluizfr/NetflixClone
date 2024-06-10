import StreamingTrackVideo from './StreamingTrackVideo';
import Loading from '../Loading';

import { GetMediaSignedCookieService } from '@Services/GetMediaSignedCookie/GetMediaSignedCookieService';
import { GetTrackService } from '@Services/GetTrack/GetTrackService';
import { GetMediaService } from '@Services/GetMedia/GetMediaService';

import { useSearchParams } from 'react-router-dom';

export default function TrackPageProvider () {

    const [searchParams] = useSearchParams();
    const trackId = searchParams.get("trackMetadataId");
    const mediaId = searchParams.get("mediaId");

    const getMediaSignedCookieResult = GetMediaSignedCookieService();
    const getMediaServiceResult = GetMediaService(mediaId);
    const getTrackServiceResult = GetTrackService(trackId);

    if(getMediaSignedCookieResult.isSuccess && getMediaServiceResult.data?.data && getTrackServiceResult.data?.data)
        return (
            <div>
                <StreamingTrackVideo media={getMediaServiceResult.data.data} trackMetadata={getTrackServiceResult.data.data}/>
            </div>
        );
    else
        return <Loading/>
}