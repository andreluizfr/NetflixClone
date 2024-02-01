export interface Track {
    id: string;
    title: string | null;   //nulo quando é filme
    thumbnailUrl: string | null; //nulo quando é filme
    trackUrl: string | null; //nulo quando é filme
    duration: number; //em minutos
    order: number; //posição
    created_at: Date;
}

export function formatDuration(duration: number): string {
    const minutes = duration % 60;
    const hours = Math.floor(duration / 60);

    if(hours > 0)
        return `${hours}h ${minutes}min`;
    else 
        return `${minutes}min`;
}