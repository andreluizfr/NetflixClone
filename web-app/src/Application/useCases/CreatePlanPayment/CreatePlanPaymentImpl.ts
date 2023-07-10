import { makeQueryManager } from "@Main/factories/infrastructure/makeQueryManager";
import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { ICreatePlanPayment } from "@Application/useCases/CreatePlanPayment/ICreatePlanPayment";
import { HttpStatusCode } from "@Application/interfaces/httpClient/HttpStatusCode";
import { IHttpError } from "@Application/interfaces/httpClient/IHttpError";
import { IHttpResponse } from "@Application/interfaces/httpClient/IHttpResponse";

import { Plan } from "@Model/entities/Account";
import { PaymentType } from "@Model/types/PaymentType";
import { PreferenceResponse } from "@Model/types/PreferenceResponse";

import { removeUser } from '@Infrastructure/stores/redux/features/userSlice';

import { AnyAction } from "@reduxjs/toolkit";
import { Dispatch, useEffect } from "react";
import { useDispatch } from "react-redux";

import { NavigateFunction, useNavigate } from "react-router-dom";



export const  CreatePlanPaymentImpl: ICreatePlanPayment = (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
) => {

    const queryResult = makeQueryManager<PreferenceResponse>(
        async () => createPlanPaymentHttpRequest(accountId, plan, paymentType),
        'createPlanPayment',
        {
            enabled: true,
            retry: 3
        }
    );
    
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) HandleCreatePlanPaymentQueryError(queryResult.error, dispatch, navigate);
        else if (queryResult.data?.data) HandleCreatePlanPaymentQuerySuccess(queryResult.data, navigate);
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [queryResult]);

    return queryResult;
}

async function createPlanPaymentHttpRequest (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
){

    const accessToken = localStorage.getItem("x-access-token");

    if(accessToken)
        throw {
            httpStatusCode: null,
            message: 'Erro: Token de acesso não encontrado.'
        } as IHttpError;

    const httpClient = makeHttpClient<PreferenceResponse>();

    const httpResponse = httpClient.post(
        '/payment/createPlanPayment',
        {accountId, plan, paymentType}
    );
      
    return httpResponse;
}

function HandleCreatePlanPaymentQuerySuccess(data: IHttpResponse<PreferenceResponse>, navigate: NavigateFunction) {

    console.log(data);
    
    //const preferenceResponse = data.data;

    navigate("/login");
}

function HandleCreatePlanPaymentQueryError(httpError: IHttpError, dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.error(httpError);

    switch(httpError.httpStatusCode){ 

        case HttpStatusCode.Bad_Request: {  //Bad Request. Parâmetros errados, então remover conta salva e reiniciar o processo
            dispatch(removeUser());
            setTimeout(()=>navigate("/signup"), 2000);
            break;
        }
        case HttpStatusCode.Not_Found:{ //Not Found. Conta do usuário não foi encontrada, então remover conta salva e reiniciar o processo
            dispatch(removeUser()); 
            setTimeout(()=>navigate("/signup"), 2000);
            break
        }
        case HttpStatusCode.Bad_Gateway: { //Bad Gateway. Erro com a api de pagamento, não há tratamento
            break;
        }
        case HttpStatusCode.Internal_Server_Error: { //Internal Server Error. Erro em runtime desconhecido, não há tratamento
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
