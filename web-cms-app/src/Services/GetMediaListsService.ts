import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";
import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { MediaList } from "@Model/entities/MediaList";

import { removeUser } from "@Infrastructure/store/redux/features/userSlice";
import { AnyAction, Dispatch } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";

import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";

import { toast } from 'react-toastify';
import { generateErrorMessage } from "@Main/utils/AuxiliaryFunctions";

export const GetMediaListsService = () => {

    const queryResult = useQuery<IHttpResponse<MediaList[]>, IHttpError>(
        ['getMediaLists'],
        async () => GetMediaListsHttpRequest(),
        {
            enabled: true,
            staleTime: 60 * 1000,
            cacheTime: 60 * 60 * 1000,
        }
    );
    
    const dispatch = useDispatch(); 
    
    useEffect(()=>{
        if (queryResult.isError && queryResult.error) {
            HandleGetMediaListsQueryError(queryResult.error, dispatch);

            toast.error(generateErrorMessage(queryResult.error.httpStatusCode, queryResult.error.message), {
                position: "bottom-left",
                hideProgressBar: true
            });
        }
        else if (queryResult.data?.data) {
            HandleGetMediaListsQuerySuccess(queryResult.data);
        }
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult;
}

export async function GetMediaListsHttpRequest (): Promise<IHttpResponse<MediaList[]>>{

    const persistentStorage = makePersistentStorage();

    const accessToken = persistentStorage.get<string>("x-access-token");

    if(!accessToken)
        throw {
            httpStatusCode: null,
            message: 'Erro: Token de acesso não encontrado.'
        } as IHttpError;

    const httpClient = makeHttpClient<MediaList[]>();

    const httpResponse = httpClient.get(
        "/mediaList/getAll",
        {headers: { Authorization: `Bearer ${accessToken}` }}
    );

    return httpResponse;  
}

function HandleGetMediaListsQuerySuccess(data: IHttpResponse<MediaList[]>) {

    console.log(data);
}

function HandleGetMediaListsQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Forbidden: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
    }
}