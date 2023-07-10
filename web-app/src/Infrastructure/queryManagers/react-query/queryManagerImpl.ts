import { IHttpResponse } from '@Application/interfaces/httpClient/IHttpResponse';
import { IHttpError } from '@Application/interfaces/httpClient/IHttpError';
import { IQueryManager } from '@Application/interfaces/queryManager/IQueryManager';
import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

import { useQuery } from 'react-query';

export const ReactQueryManagerImpl: IQueryManager = <T>(
    httpRequest: () => Promise<IHttpResponse<T>>,
    key?: string,
    options?: object
): IQueryResult<T> => {
    
    const checkedKey = key || "none";

    const query = useQuery<IHttpResponse<T>, IHttpError>(checkedKey, httpRequest, options);

    const isLoading = query.isFetching || query.isLoading;
    const isError = query.isError;
    const error = query.error;
    const data = query.data;
    const refetch = query.refetch;
    const remove = query.remove;

    return {isLoading, isError, error, data, refetch, remove};

}