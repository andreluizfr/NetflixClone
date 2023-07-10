import { Media } from "./Media";

export interface Movie extends Media{
    isMovieSeries: boolean,
    sequenceNumber: number,
    actorsActresses: string[]
}