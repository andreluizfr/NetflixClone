export enum Genre{
    COMEDY = 0,
    ACTION = 1,
    THRILLER = 2,
    HORROR = 3,
    MYSTERY = 4,
    FANTASY = 5,
    ROMANCE = 6,
    SCIENCE_FICTION = 7,
    SLICE_OF_LICE = 8,
    DOCUMENTARY = 9
}

export interface Media{
    id: number,
    title: string,
    isAnimation: boolean,
    genres: Genre[],
    director: string,
    releaseYear: number,
    descriptions: string,
    ageRating: number,
    thumbnailUrl: string,
    posterUrl: string,
    trailerUrl: string
    created_at: Date
}