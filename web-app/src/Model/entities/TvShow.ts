import { EpisodeTrack } from "./Track";
import { Media } from "./Media";

export interface TvShow extends Media{
    numberOfSeasons: number,
    seasonNumber: number,
    actorsActresses: string[],
    episodeTracks: EpisodeTrack[]
}

export function isTvShow (o: object): boolean {
    if (o && ('numberOfSeasons' in o) && ('seasonNumber' in o) && ('actorsActresses' in o)) return true;
    else return false;
}