import { Profile } from "./Profile";

export enum Plan {
    BASIC_WITH_ADS, BASIC, PREMIUM
}

export interface Account{
    id: string;
    active: boolean;
    currentPlan: Plan;
    planExpireDate: Date;
    paymentHistory: object[];
    profiles: Profile[];
    limitOfProfiles: number;
    createdAt: Date;
}