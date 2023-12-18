import { Media } from "./Media";

export interface MediaList {
    id: string,
    title: string,
    medias: Media[],
    created_at: Date
}