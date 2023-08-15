import { Episode } from "./Episode";
import { Media } from "./Media";

export interface Movie extends Media{
    movieSeries: boolean,
    sequenceNumber: number,
    actorsActresses: string[],
    episode: Episode
}

export function isMovie (o: object): boolean {
    if (o && ('movieSeries' in o) && ('sequenceNumber' in o) && ('actorsActresses' in o)) return true;
    else return false;
}