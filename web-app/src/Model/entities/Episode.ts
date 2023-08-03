export interface Episode{
    id: string;
    title: string | null;   //nulo quando é filme
    thumbnailUrl: string | null; //nulo quando é filme
    episodeUrl: string | null; //nulo quando é filme
    duration: number; //em minutos
    order: number; //posição
    created_at: Date;
}