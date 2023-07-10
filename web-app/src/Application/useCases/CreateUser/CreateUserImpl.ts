import { makeQueryManager } from "@Main/factories/infrastructure/makeQueryManager";
import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { ICreateUser } from "@Application/useCases/CreateUser/ICreateUser";
import { HttpStatusCode } from "@Application/interfaces/httpClient/HttpStatusCode";
import { IHttpError } from "@Application/interfaces/httpClient/IHttpError";
import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

import { saveEmail, saveStep } from "@Infrastructure/stores/redux/features/signupDataSlice";

import { User } from "@Model/entities/User";

import { AnyAction } from "@reduxjs/toolkit";
import { useDispatch } from "react-redux";
import { Dispatch, useEffect } from "react";
import { NavigateFunction, useNavigate } from "react-router-dom";


export const CreateUserImpl: ICreateUser = (email, password, birthDate) => {

    const queryResult = makeQueryManager<User>(async () => CreateUserHttpRequest(email, password, birthDate), 'createUser');

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleCreateUserQueryError(queryResult.error, dispatch, navigate);
        else if (queryResult.data?.data) HandleCreateUserQuerySuccess(queryResult.data, navigate);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult]);

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


function HandleCreateUserQuerySuccess(data: IHttpResponse<User>, navigate: NavigateFunction) {

    console.log(data);

    const user = data.data;

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