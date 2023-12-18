import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";
import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { Media } from "@Model/entities/Media";

import { removeUser } from "@Infrastructure/store/redux/features/userSlice";
import { AnyAction, Dispatch } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";

import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

export const GetMediasService = () => {

    const queryResult = useQuery<IHttpResponse<Media>, IHttpError>(
        ['getMedias'],
        async () => GetMediasHttpRequest(),
        {
            enabled: true,
            staleTime: 5 * 1000,
            cacheTime: 2 * 60 * 60 * 1000,
            refetchInterval: 30 * 1000,
        }
    );
    
    const dispatch = useDispatch(); 
    
    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleGetMediasQueryError(queryResult.error, dispatch);
        else if (queryResult.data?.data) HandleGetMediasQuerySuccess(queryResult.data);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult; //para fazer o devido uso com relação a camada de view do react
}

export async function GetMediasHttpRequest (): Promise<IHttpResponse<Media>>{

    const httpClient = makeHttpClient<Media>();

    const httpResponse = httpClient.get("/medias/getAll");

    return httpResponse;  
}

function HandleGetMediasQuerySuccess(data: IHttpResponse<Media>) {

    console.log(data);
}

function HandleGetMediasQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Forbidden: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }

        default: { //erro não mapeado pelo controlador do backend, não há tratamento
            break;
        }
    }
}