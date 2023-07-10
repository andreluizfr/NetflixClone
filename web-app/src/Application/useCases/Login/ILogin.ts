import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

export type ILogin = (
    email: string | null,
    password: string | null
) => IQueryResult<string>;