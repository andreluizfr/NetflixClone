import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { User } from "@Model/entities/User";

import { saveUser, removeUser } from '@Infrastructure/store/redux/features/userSlice';

import { useQuery } from "@tanstack/react-query";
import { AnyAction } from "@reduxjs/toolkit";
import { Dispatch, useEffect } from "react";
import { useDispatch } from "react-redux";

export const FetchUserService = () => {

    const dispatch = useDispatch();

    const queryResult = useQuery<IHttpResponse<User>, IHttpError>(
        ['fetchUser'],
        async () => FetchUserHttpRequest(),
        {
            enabled: true,
            staleTime: 5 * 1000,
            cacheTime: 60 * 60 * 1000,
            initialData: ()=>{
                const persistentStorage = makePersistentStorage();
                const user = persistentStorage.get<User>("user");

                if(user)
                    return {
                        message: "Usuário vindo pelo storage", 
                        data: user
                    };
                else return undefined;
            },
        }
    );

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) {
            HandleFetchUserQueryError(dispatch);
        }
        else if (queryResult.data?.data) {
            HandleFetchUserQuerySuccess(queryResult.data, dispatch);
        }
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult;
}

export async function FetchUserHttpRequest (){

    const persistentStorage = makePersistentStorage();

    const accessToken = persistentStorage.get<string>("x-access-token");

    if(!accessToken)
        throw {
            httpStatusCode: null,
            message: 'Erro: Token de acesso não encontrado.'
        } as IHttpError;
    
    const httpClient = makeHttpClient<User>();

    const httpResponse = httpClient.get(
        '/user/fetch',
        {headers: { Authorization: `Bearer ${accessToken}` }}
    );

    return httpResponse;
}

function HandleFetchUserQuerySuccess(data: IHttpResponse<User>, dispatch: Dispatch<AnyAction>) {

    const user = data.data;

    if(user) dispatch(saveUser(user));
    else throw new Error("Error: usuário nulo");
}

function HandleFetchUserQueryError(dispatch: Dispatch<AnyAction>) {

    dispatch(removeUser());
    window.location.href = import.meta.env.VITE_APP_BASE_URL;
}
