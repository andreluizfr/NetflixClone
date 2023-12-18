import { Media } from "./Media";

export interface Profile{
    ownerName: string,
    iconCod: string,
    seenMedias: Media[],
    preferences: object
}