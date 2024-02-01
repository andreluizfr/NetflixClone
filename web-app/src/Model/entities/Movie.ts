import { Track } from "./Track";
import { Media } from "./Media";

export interface Movie extends Media{
    isMovieSeries: boolean,
    sequenceNumber: number,
    actorsActresses: string[],
    track: Track
}

export function isMovie (o: object): boolean {
    if (o && ('isMovieSeries' in o) && ('sequenceNumber' in o) && ('actorsActresses' in o)) return true;
    else return false;
}