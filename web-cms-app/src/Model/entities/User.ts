import { Account } from "@Model/entities/Account";

export enum Role{
    BASIC, ADMIN
}

export interface User {
    id: string,
    email: string,
    birthDate: Date,
    role: Role,
    account: Account,
    createdAt: Date
}