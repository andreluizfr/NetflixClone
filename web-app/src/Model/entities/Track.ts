export class TrackMetadata {
    id!: string;
    processingStatus!: TrackProcessingStatus;
    createdAt!: Date;
    updatedAt!: Date;
}

export class EpisodeTrack extends TrackMetadata {
    title!: string;
    duration!: number;
    order!: number;
    season!: number;
}

export enum TrackProcessingStatus {
    NOT_PROCESSED,
    IN_QUEUE,
    PROCESSING,
    PROCESSED,
    ERROR
}

export function formatDuration(duration: number): string {
    const minutes = duration % 60;
    const hours = Math.floor(duration / 60);

    if(hours > 0)
        return `${hours}h ${minutes}min`;
    else 
        return `${minutes}min`;
}