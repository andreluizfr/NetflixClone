import { IHttpClient } from '@Infrastructure/httpClient/IHttpClient';
import { IHttpResponse } from '@Infrastructure/httpClient/IHttpResponse';
import { IHttpError } from '@Infrastructure/httpClient/IHttpError';

import { AxiosError, AxiosInstance } from 'axios';
import { HttpStatusCode } from '../HttpStatusCode';

export class AxiosHttpClientImpl<T> implements IHttpClient<T> {

    axiosInstance;

    constructor(instance: AxiosInstance){
        this.axiosInstance = instance;
    }

    async get(path: string, header?: object){

        try{
            const response = await this.axiosInstance.get<IHttpResponse<T>>(path,header);

            return response.data;

        } catch (err: unknown) {

            throw this.generateHttpError(err);
        }
    }

    async post(path: string, body: object, header?: object){

        try{
            const response = await this.axiosInstance.post<IHttpResponse<T>>(path, body, header);

            return response.data;

        } catch (err: unknown) {

            throw this.generateHttpError(err);
        }
    }

    generateHttpError(err: unknown): IHttpError {

        const error = err as AxiosError<IHttpResponse<T>>;

        if (error.response) return {
            httpStatusCode: error.response.status,
            message:  error.response.data.message
        };

        else if (error.request) return {
            httpStatusCode: HttpStatusCode.Bad_Gateway,
            message:  'Erro: O servidor não pode responder a essa requisição.'
        };

        else return {
            httpStatusCode: HttpStatusCode.Bad_Gateway,
            message:  error.message
        };
    }
}