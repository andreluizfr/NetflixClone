import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";
import { IQueryResult } from "@Application/interfaces/queryManager/IQueryResult";

//Bridge pattern
export type IQueryManager = <T>(
    httpRequest: () => Promise<IHttpResponse<T>>,
    key?: string,
    options?: object
) => IQueryResult<T>;
