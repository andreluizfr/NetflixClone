import { Media } from "./Media";
import { EpisodeTrack } from "./Track";

export interface Movie extends Media{
    isMovieSeries: boolean,
    sequenceNumber: number,
    actorsActresses: string[],
    episodeTrack: EpisodeTrack
}

export function isMovie (o: object): boolean {
    if (o && ('isMovieSeries' in o) && ('sequenceNumber' in o) && ('actorsActresses' in o)) return true;
    else return false;
}