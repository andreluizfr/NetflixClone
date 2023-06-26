import SeenShow from "./SeenShow";

export default interface Profile{
    name: string,
    iconUrl: string,
    seenShows: SeenShow[],
    preferences: object
}