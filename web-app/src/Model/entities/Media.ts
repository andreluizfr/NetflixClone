export function normalizeGenreList(list: string[]){
    return list.map((genre: string)=>{
        switch(genre){
            case "COMEDY": return "Comédia";
            case "ACTION": return "Ação";
            case "THRILLER": return "Suspense";
            case "HORROR": return "Terror";
            case "MYSTERY": return "Mistério";
            case "FANTASY": return "Fantasia";
            case "ROMANCE": return "Romanticos";
            case "SCIENCE_FICTION": return "Ficção científica";
            case "SLICE_OF_LICE": return "Dia-a-dia";
            case "DOCUMENTARY": return "Documentário";
            default: return "Desconhecido";
        }
    });
}

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
    genres: string[],
    director: string,
    releaseYear: number,
    descriptions: string,
    ageRating: number,
    thumbnailUrl: string,
    posterUrl: string,
    trailerUrl: string
    created_at: Date
}