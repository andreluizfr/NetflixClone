import { Media } from "./Media";

export interface Anime extends Media{
    numberOfSeasons: number,
    seasonNumber: number,
    studio: string
}

export function isAnime (o: object): boolean {
    if (o && ('numberOfSeasons' in o) && ('seasonNumber' in o) && ('studio' in o)) return true;
    else return false;
}