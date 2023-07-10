import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";
import { IHttpError } from "@Application/interfaces/httpClient/IHttpError";

export interface IQueryResult<T> {
    isLoading: boolean;
    isError: boolean;
    error: IHttpError | null;
    data: IHttpResponse<T> | undefined;
    refetch: () => any;
}
