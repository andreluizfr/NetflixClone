import { makeQueryManager } from "@Main/factories/infrastructure/makeQueryManager";
import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { IFetchUser } from "@Application/useCases/FetchUser/IFetchUser";
import { HttpStatusCode } from "@Application/interfaces/httpClient/HttpStatusCode";
import { IHttpError } from "@Application/interfaces/httpClient/IHttpError";
import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

import { User } from "@Model/entities/User";

import { saveUser, removeUser } from '@Infrastructure/stores/redux/features/userSlice';

import { AnyAction } from "@reduxjs/toolkit";
import { Dispatch, useEffect } from "react";
import { useDispatch } from "react-redux";


export const FetchUserImpl: IFetchUser = () => {

    const queryResult = makeQueryManager<User>(
        async () => FetchUserHttpRequest(),
        'fetchUser',
        {
            enabled: true,
            staleTime: 1 * 1000, //Until 1s it will not make the Request. When the 1s pass it uses cache as fallback while loading the query
            cacheTime: 60 * 60 * 1000, //after 1 hour, the cache it will be invalid and it will no be used as fallback if the data is stale. It has to be greater than staleTime
            refetchInterval: 2 * 1000, //run each 2s
            initialData: ()=>{
                let data;
                const user = localStorage.getItem("user");

                if(user) data = JSON.parse(user) as User;
                else data = null;

                return {
                    message: "fetched user data in storage", 
                    data: data
                };
            },
        }
    );
    
    const dispatch = useDispatch();
    //const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleFetchUserQueryError(queryResult.error, dispatch);
        else if (queryResult.data?.data) HandleFetchUserQuerySuccess(queryResult.data, dispatch);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult]);

    return queryResult;
}

async function FetchUserHttpRequest (){

    const accessToken = localStorage.getItem("x-access-token");

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
        case HttpStatusCode.Internal_Server_Error: { //Internal server error. erro durante a criaçao das entidades, não há tratamento
            break;
        }
        case null: { //Erro desconhecido, servidor indisponível, ou não passou em validação para inciar a request
            break;
        }

        default: { //erro não mapeado pelo controlador do backend, não há tratamento
            break;
        }
    }
}
