import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { HttpStatusCode } from "@Application/interfaces/httpClient/HttpStatusCode";
import { IHttpError } from "@Application/interfaces/httpClient/IHttpError";
import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

import { PreviewMedia } from "@Model/entities/PreviewMedia";

import { removeUser } from "@Infrastructure/stores/redux/features/userSlice";
import { AnyAction, Dispatch } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";

import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";


export const GetCurrentPreviewMediaService = () => {

    const queryResult = useQuery<IHttpResponse<PreviewMedia>, IHttpError>(
        ['getCurrentPreviewMedia'],
        async () => GetMediaListsHttpRequest(),
        {
            enabled: true,
            staleTime: 60 * 1000,
            cacheTime: 60 * 60 * 1000,
        }
    );
    
    const dispatch = useDispatch(); 
    
    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleQueryError(queryResult.error, dispatch);
        else if (queryResult.data?.data) HandleQuerySuccess(queryResult.data);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult; //para fazer o devido uso com relação a camada de view do react
}

export async function GetMediaListsHttpRequest (): Promise<IHttpResponse<PreviewMedia>>{

    const persistentStorage = makePersistentStorage();

    const accessToken = persistentStorage.get<string>("x-access-token");

    if(!accessToken)
        throw {
            httpStatusCode: null,
            message: 'Erro: Token de acesso não encontrado.'
        } as IHttpError;

    const httpClient = makeHttpClient<PreviewMedia>();

    const httpResponse = httpClient.get(
        "/previewMedia/getCurrent",
        {headers: { Authorization: `Bearer ${accessToken}` }}
    );

    return httpResponse;  
}

function HandleQuerySuccess(data: IHttpResponse<PreviewMedia>) {

    console.log(data);
}

function HandleQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Forbidden: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
        case HttpStatusCode.Not_Found: { //Não há previewMedia no banco
            break;
        }
        case HttpStatusCode.Internal_Server_Error: { //Erro durante a busca de previewMedia
            break;
        }
        case null: { //Erro desconhecido, servidor indisponível, ou não passou em validação para inciar a request
            break;
        }

        default: { //erro não mapeado pelo controlador do backend
            break;
        }
    }
}