import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { useQuery } from "@tanstack/react-query";

export const GetMediaSignedCookieService = () => {

    const queryResult = useQuery<IHttpResponse<null>, IHttpError>(
        ['getMediaSignedCookie'],
        async () => GetMediaSignedCookieHttpRequest(),
        {
            staleTime: 24 * 60 * 60 * 1000, //colocar o tempo que dura o signed cookie
            cacheTime: 0,
        }
    );

    return queryResult; //para fazer o devido uso com relação a camada de view do react
}

export async function GetMediaSignedCookieHttpRequest (): Promise<IHttpResponse<null>>{

    const httpClient = makeHttpClient<null>();

    const httpResponse = httpClient.get("/track/signedCookie");

    return httpResponse;  
}