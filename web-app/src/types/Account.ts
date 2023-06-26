import Plan from "./Plan";
import Profile from "./Profile";

export interface Account{
    id: string;
    isActive: boolean;
    currentPlan: Plan;
    planExpireDate: Date;
    paymentHistory: Object[];
    profiles: Profile[];
    limitOfProfiles: number;
    createdAt: Date;
}