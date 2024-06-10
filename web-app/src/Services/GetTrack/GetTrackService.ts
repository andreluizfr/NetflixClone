import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";
import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { removeUser } from '@Infrastructure/store/redux/features/userSlice';

import { useQuery } from "@tanstack/react-query";
import { AnyAction } from "@reduxjs/toolkit";
import { Dispatch, useEffect } from "react";
import { useDispatch } from "react-redux";
import { TrackMetadata } from "@Model/entities/Track";


export const GetTrackService = (trackId: string | null) => {

    const queryResult = useQuery<IHttpResponse<TrackMetadata>, IHttpError>(
        ['getTrack'],
        async () => GetTrackHttpRequest(trackId),
        {
            staleTime: 3 * 60 * 60 * 1000, //colocar o tempo que dura o signed cookie
            cacheTime: 0,
        }
    );
    
    const dispatch = useDispatch();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleFetchUserQueryError(queryResult.error, dispatch);
        else if (queryResult.data?.data) HandleFetchUserQuerySuccess(queryResult.data);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult;
}

export async function GetTrackHttpRequest (trackId: string | null){
    
    const persistentStorage = makePersistentStorage();

    const accessToken = persistentStorage.get<string>("x-access-token");

    if(!accessToken)
        throw {
            httpStatusCode: null,
            message: 'Erro: Token de acesso não encontrado.'
        } as IHttpError;

    if(!trackId)
        throw {
            httpStatusCode: null,
            message: 'Erro: Id do título não identificado.'
        } as IHttpError;

    const httpClient = makeHttpClient<TrackMetadata>();

    const httpResponse = httpClient.get('/track/episodeTrack?id='+trackId);

    return httpResponse;
}

function HandleFetchUserQuerySuccess(data: IHttpResponse<TrackMetadata>) {

    console.log(data);
}

function HandleFetchUserQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Forbidden: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
    }
}
