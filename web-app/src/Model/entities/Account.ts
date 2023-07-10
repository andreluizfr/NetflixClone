export default interface SeenShow{
    id: number,
    title: string,
    thumbnailUrl: string
}

export interface Profile{
    name: string,
    iconUrl: string,
    seenShows: SeenShow[],
    preferences: object
}

export enum Plan {
    BASIC_WITH_ADS, BASIC, PREMIUM
}

export interface Account{
    id: string;
    isActive: boolean;
    currentPlan: Plan;
    planExpireDate: Date;
    paymentHistory: object[];
    profiles: Profile[];
    limitOfProfiles: number;
    createdAt: Date;
}