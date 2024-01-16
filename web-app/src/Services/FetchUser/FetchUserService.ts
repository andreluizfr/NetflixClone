import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";
import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { User } from "@Model/entities/User";

import { saveUser, removeUser } from '@Infrastructure/store/redux/features/userSlice';

import { useQuery } from "@tanstack/react-query";
import { AnyAction } from "@reduxjs/toolkit";
import { Dispatch, useEffect } from "react";
import { useDispatch } from "react-redux";


export const FetchUserService = () => {

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
                        message: "Usuário dado pelo storage", 
                        data: user
                    };
                else return undefined;
            },
        }
    );
    
    const dispatch = useDispatch();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleFetchUserQueryError(queryResult.error, dispatch);
        else if (queryResult.data?.data) HandleFetchUserQuerySuccess(queryResult.data, dispatch);
    // eslint-disable-next-line react-hooks/exhaustive-deps
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

    console.log(data);

    const user = data.data;

    if(user) dispatch(saveUser(user));
}

function HandleFetchUserQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Forbidden: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
        case HttpStatusCode.Internal_Server_Error: { //Internal server error. Não sabemos qual motivo, mas tem que sair igual
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
        case null: { //Erro desconhecido, servidor indisponível, ou não passou em validação para inciar a request
            dispatch(removeUser()); //se de algum jeito tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }

        default: { //erro não mapeado pelo controlador do backend. Não sabemos qual motivo, mas tem que sair igual
            dispatch(removeUser()); //se tiver algum salvo
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
            break;
        }
    }
}
