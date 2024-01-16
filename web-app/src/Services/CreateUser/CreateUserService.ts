import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { HttpStatusCode } from "@Infrastructure/httpClient/HttpStatusCode";
import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { saveEmail, saveStep } from "@Infrastructure/store/redux/features/signupDataSlice";

import { User } from "@Model/entities/User";

import { useQuery } from "@tanstack/react-query";
import { AnyAction } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";
import { Dispatch, useEffect } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";
import { saveUser } from "@Infrastructure/store/redux/features/userSlice";

export const CreateUserService = (
    email: string | null,
    password: string | null,
    birthDate: Date | null
) => {

    const queryResult = useQuery<IHttpResponse<User>, IHttpError>(
        ['createUser'],
        async () => CreateUserHttpRequest(email, password, birthDate)
    );

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleCreateUserQueryError(queryResult.error, dispatch, navigate);
        else if (queryResult.data?.data) HandleCreateUserQuerySuccess(queryResult.data, dispatch, navigate);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult; //para fazer o devido uso com relação a camada de view do react
}

async function CreateUserHttpRequest (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
): Promise<IHttpResponse<User>>{
    
    if(!email || !password || !birthDate)
        throw {
            httpStatusCode: null,
            message: "Erro: Email, senha ou data de aniversário não foram identificados."
        } as IHttpError;

    const httpClient = makeHttpClient<User>();

    const httpResponse = httpClient.post(
        '/user/create',
        {email, password, birthDate},
    )

    return httpResponse;
}


function HandleCreateUserQuerySuccess(data: IHttpResponse<User>, dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.log(data);

    const user = data.data;

    dispatch(saveUser(user));

    if(user) navigate("/signup/payment");
}


function HandleCreateUserQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Bad_Request: {  //Bad Request. Erro nos parametros passados, reiniciar processo de registro
            setTimeout(()=>navigate("/signup"), 2000);
            break;
        }
        case HttpStatusCode.Unprocessable_Entity: { //Unprocessable entity. Email já está em uso, voltar para etapa de colocar email
            dispatch(saveEmail(null));
            dispatch(saveStep(2));
            setTimeout(()=>navigate("/signup/registration"), 2000);
            break;
        }
        case HttpStatusCode.Internal_Server_Error: { //Internal server error. erro durante a criaçao das entidades, não há tratamento
            break;
        }

        case null: { //Erro desconhecido, servidor indisponível, ou não passou em validação para inciar a request
            setTimeout(()=>navigate("/signup"), 2000);
            break;
        }
        default: { //erro não mapeado pelo controlador do backend, não há tratamento
            break;
        }
    }

}