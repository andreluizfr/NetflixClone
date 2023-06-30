import { useQuery } from 'react-query';
import { QueryError } from '../../libs/react-query';

import { AxiosError } from 'axios';
import axios, { CustomResponse } from '../../libs/axios';

import { NavigateFunction, useNavigate } from 'react-router-dom';

export default function LoginQuery (
    email: string | null,
    password: string | null,
) {

    const loginQuery = useQuery<CustomResponse<string>['data'], QueryError>('login', async () => login(email, password));
    
    const navigate = useNavigate();

    if (loginQuery.data) HandleLoginQuerySuccess(loginQuery.data, navigate);

    else if (loginQuery.isError) HandleLoginQueryError(loginQuery.error);

    return loginQuery; //para fazer o devido uso com relação a camada de view do react

}

async function login (
    email: string | null,
    password: string | null,
){
    
    if(email && password){
        
        try{

            const response = await axios.post<CustomResponse<string>["data"]>(
                '/auth/login',
                {email, password},
            );

            return response.data;

        } catch (err: unknown) {

            const error = err as AxiosError<CustomResponse<string>["data"]>;

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
        message: 'Erro: Email ou senha não foram identificados.'
    };

}

function HandleLoginQuerySuccess(data: CustomResponse<string>['data'], navigate: NavigateFunction) {

    console.log(data);

    const accessToken = data.data;

    if(accessToken){
        localStorage.setItem("x-access-token", accessToken);
        navigate("/contents", { replace: true });
    }
}

function HandleLoginQueryError(queryError: QueryError) {

    console.error(queryError.message);

    switch(queryError.httpStatusCode){ 

        case 400: { //Bad Request. Erro nos parametros passados. Não há tratamento
            break;
        }
        case 403: { //Forbidden. Email ou senha errada. Não há tratamento
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