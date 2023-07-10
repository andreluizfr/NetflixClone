import { IQueryManager } from "@Application/interfaces/queryManager/IQueryManager";
import { IQueryResult } from "@Application/interfaces/queryManager/IQueryResult";
import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

import { ReactQueryManagerImpl } from "@Infrastructure/queryManagers/react-query/queryManagerImpl";
//import { CustomQueryManager } from "@Infrastructure/queryManagers/CustomQueryManagerImpl";

//Factory method pattern
export const makeQueryManager: IQueryManager = <T>(
    httpRequest: () => Promise<IHttpResponse<T>>,
    key?: string,
    options?: object
): IQueryResult<T> => {

    const queryManager = ReactQueryManagerImpl<T>(httpRequest, key, options);
    //const queryManager = CustomQueryManager<T>(httpRequest);

    return queryManager;
}