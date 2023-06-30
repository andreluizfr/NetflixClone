import { useQuery } from 'react-query';
import { QueryError } from '../../libs/react-query';

import { AxiosError } from 'axios';
import axios, { CustomResponse } from '../../libs/axios';

import { User } from '../../types/User';

import { useDispatch } from 'react-redux';
import { addUser, removeUser } from '../../store/features/userSlice';

import { AnyAction, Dispatch } from '@reduxjs/toolkit';


export default function FetchUserQuery () {

    const fetchUserQuery = useQuery<CustomResponse<User>['data'], QueryError>(
        'fetchUser',
        async () => fetchUser(),
        {
            enabled: true,
            staleTime: 30 * 1000, //30s. Until 30s it will not make the Request. When the 30s pass it uses cache as fallback while loading the query
            cacheTime: 60 * 60 * 1000, //after 1 hour, the cache it will be invalid and it will no be used as fallback if the data is stale. It has to be greater than staleTime
            initialData: ()=>{
                let data;
                const user = localStorage.getItem("user");

                if(user) data = JSON.parse(user) as User;
                else data = null;

                return {
                    message: "fetching storaged user data", 
                    data: data
                };
            },
        }
    );
    
    const dispatch = useDispatch();
    
    if (fetchUserQuery.data) HandleFetchUserQuerySuccess(fetchUserQuery.data, dispatch);

    else if (fetchUserQuery.isError) HandleFetchUserQueryError(fetchUserQuery.error, dispatch);

    return fetchUserQuery;

}

async function fetchUser (){

    const accessToken = localStorage.getItem("x-access-token");

    if(accessToken){

        try{

            const response = await axios.get<CustomResponse<User>["data"]>(
                '/user/fetch', 
                {headers: { Authorization: `Bearer ${accessToken}` }}
            );

            return response.data;

        } catch (err: unknown) {

            const error = err as AxiosError<CustomResponse<User>["data"]>;

            if (error.response) throw {
                httpStatusCode: error.response.status,
                message:  error.response.data.message
            };

            else if (error.request) throw {
                httpStatusCode: null,
                message:  'Erro: O servidor não pode responder a essa requisição.'
            };

            else throw {
                httpStatusCode: null,
                message:  error.message
            };

        }

    } else throw {
        httpStatusCode: null,
        message: 'Erro: Token de acesso não encontrado.'
    };

}

function HandleFetchUserQuerySuccess(data: CustomResponse<User>['data'], dispatch: Dispatch<AnyAction>) {

    console.log(data);

    const user = data.data;

    if(user)
        dispatch(addUser(user));
  
}

function HandleFetchUserQueryError(queryError: QueryError, dispatch: Dispatch<AnyAction>) {

    console.error(queryError.message);

    switch(queryError.httpStatusCode){ 

        case 403: {  //Forbidden. Token inválido ou expirado 
            dispatch(removeUser()); //se tiver algum salvo
            break;
        }
        case 500: { //Internal server error. erro durante a criaçao das entidades, não há tratamento
            break;
        }
        case null: { //Erro antes de receber resposta do servidor, não há tratamento
            break;
        }

        default: { //erro não mapeado pelo controlador do backend, não há tratamento
            break;
        }

    }

}
