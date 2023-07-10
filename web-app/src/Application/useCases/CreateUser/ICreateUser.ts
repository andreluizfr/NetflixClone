import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

import { User } from '@Model/entities/User';


export type ICreateUser = (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
) => IQueryResult<User>;