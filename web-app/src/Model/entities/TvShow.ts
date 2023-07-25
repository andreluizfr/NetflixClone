import { Episode } from "./Episode";
import { Media } from "./Media";

export interface TvShow extends Media{
    numberOfSeasons: number,
    seasonNumber: number,
    actorsActresses: string[],
    episodes: Episode[]
}

export function isTvShow (o: object): boolean {
    if (o && ('numberOfSeasons' in o) && ('seasonNumber' in o) && ('actorsActresses' in o)) return true;
    else return false;
}