import { IHttpResponse } from '@Application/interfaces/httpClient/IHttpResponse';
import { IHttpError } from '@Application/interfaces/httpClient/IHttpError';
import { IQueryManager } from '@Application/interfaces/queryManager/IQueryManager';
import { IQueryResult } from '@Application/interfaces/queryManager/IQueryResult';

import { useState } from 'react';

export const CustomQueryManagerImpl: IQueryManager = <T>(
    httpRequest: () => Promise<IHttpResponse<T>>,
): IQueryResult<T> => {

    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [isError, setIsError] = useState<boolean>(false);
    const [error, setError] = useState<IHttpError | null>(null);
    const [data, setData] = useState<IHttpResponse<T> | undefined>(undefined);

    function refetch() {
        httpRequest()
        .then(response=>{
            setIsLoading(false);
            setData(response);
        })
        .catch(err=>{
            const error = err as IHttpError;
            setIsError(true);
            setError(error);
            setIsLoading(false);
        });
    }

    return {isLoading, isError, error, data, refetch};

}