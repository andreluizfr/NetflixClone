import { IHttpClient } from "@Infrastructure/httpClient/IHttpClient";

import { AxiosHttpClientImpl } from "@Infrastructure/httpClient/axios/httpClientImpl";
import { axiosInstance } from "@Infrastructure/httpClient/axios/AxiosInstance";

//Factory method pattern
export function makeHttpClient<T>(): IHttpClient<T> {

    const httpClient: IHttpClient<T> = new AxiosHttpClientImpl<T>(axiosInstance);

    return httpClient;
}