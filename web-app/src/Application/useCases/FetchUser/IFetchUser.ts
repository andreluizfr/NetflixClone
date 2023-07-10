import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

import { User } from '@Model/entities/User';


export type IFetchUser = () => IQueryResult<User>;