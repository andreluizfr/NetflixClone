import { IHttpClient } from "@Application/interfaces/httpClient/IHttpClient";

import { AxiosHttpClientImpl } from "@Infrastructure/httpClients/axios/httpClientImpl";
import { axiosInstance } from "@Infrastructure/httpClients/axios/AxiosInstance";

//Factory method pattern
export function makeHttpClient<T>(): IHttpClient<T> {

    const httpClient: IHttpClient<T> = new AxiosHttpClientImpl<T>(axiosInstance);

    return httpClient;
}