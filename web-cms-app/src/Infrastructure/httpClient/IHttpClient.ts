import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

//Bridge pattern
export interface IHttpClient<T>{
    get(path: string, header?: object) : Promise<IHttpResponse<T>>;
    post(path: string, body: object, header?: object) : Promise<IHttpResponse<T>>;
}