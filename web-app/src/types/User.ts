export enum Role{
    Commmon,
    Admin
}

export interface User {
    id?: string,
    firstName: string,
    lastName: string,
    email: string,
    birthDate: Date,
    cpf: string,
    phoneNumber: string,
    photoURL?: string,
    emailVerified: boolean,
    role: Role,
    created_at: Date
};