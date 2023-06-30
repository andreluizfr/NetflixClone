import { useQuery } from 'react-query';
import { QueryError } from '../../libs/react-query';

import { AxiosError } from 'axios';
import axios, { CustomResponse } from '../../libs/axios';

import { User } from '../../types/User';

import { useDispatch } from 'react-redux';
import { addUser } from '../../store/features/userSlice';
import { saveEmail, setStep } from '../../store/features/signupSlice';

import { NavigateFunction, useNavigate } from 'react-router-dom';
import { AnyAction, Dispatch } from '@reduxjs/toolkit';

export default function CreateUserQuery (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
) {

    const createUserQuery = useQuery<CustomResponse<User>['data'], QueryError>('createUser', async () => createUser(email, password, birthDate));
    
    const dispatch = useDispatch();
    const navigate = useNavigate();

    if (createUserQuery.data) HandleCreateUserQuerySuccess(createUserQuery.data, dispatch, navigate);

    else if (createUserQuery.isError) HandleCreateUserQueryError(createUserQuery.error, dispatch, navigate);

    return createUserQuery; //para fazer o devido uso com relação a camada de view do react

}

async function createUser (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
){
    
    if(email && password && birthDate){
        
        try{

            const response = await axios.post<CustomResponse<User>["data"]>(
                '/user/create',
                {email, password, birthDate},
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
        message: 'Erro: Email, senha ou data de aniversário não foram identificados.'
    };

}

function HandleCreateUserQuerySuccess(data: CustomResponse<User>['data'], dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.log(data);

    const user = data.data;

    if(user){
        dispatch(addUser(user));
        navigate("/signup/payment");
    }
}

function HandleCreateUserQueryError(queryError: QueryError, dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.error(queryError.message);

    switch(queryError.httpStatusCode){ 

        case 400: {  //Bad Request. Erro nos parametros passados, reiniciar processo de registro
            setTimeout(()=>navigate("/signup"), 2000);
            break;
        }
        case 422: { //Unprocessable entity. Email já está em uso, voltar para etapa de colocar email
            dispatch(saveEmail(null));
            dispatch(setStep(2));

            setTimeout(()=>navigate("/signup/registration"), 2000);

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