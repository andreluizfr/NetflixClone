import { useQuery } from 'react-query';
import { QueryError } from '../../libs/react-query';

import { AxiosError } from 'axios';
import axios, { CustomResponse } from '../../libs/axios';

import Plan from '../../types/Plan';
import PaymentType from '../../types/PaymentType';

import { useDispatch } from 'react-redux';
import { removeUser } from '../../store/features/userSlice';

import { NavigateFunction, useNavigate } from 'react-router-dom';
import { AnyAction, Dispatch } from '@reduxjs/toolkit';



export default function CreatePlanPaymentQuery (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ) {

    const createPlanPaymentQuery = useQuery<CustomResponse<PreferenceResponse>['data'], QueryError>(
        'createPlanPayment',
        async () => createPlanPayment(accountId, plan, paymentType),
        {
            enabled: true,
            retry: 3
        }
    );
    
    const dispatch = useDispatch();
    const navigate = useNavigate();
    
    if (createPlanPaymentQuery.data) HandleCreatePlanPaymentQuerySuccess(createPlanPaymentQuery.data, dispatch, navigate);

    else if (createPlanPaymentQuery.isError) HandleCreatePlanPaymentQueryError(createPlanPaymentQuery.error, dispatch, navigate);

    return createPlanPaymentQuery;

}

async function createPlanPayment (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
){

    const accessToken = localStorage.getItem("x-access-token");

    if(accessToken){

        try{

            const response = await axios.post<CustomResponse<PreferenceResponse>["data"]>(
                '/payment/createPlanPayment',
                {accountId, plan, paymentType},
            );

            return response.data;

        } catch (err: unknown) {

            const error = err as AxiosError<CustomResponse<PreferenceResponse>["data"]>;

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
        message: 'Erro: Id da conta, plano ou tipo de pagamento estão em branco.'
    };

}

function HandleCreatePlanPaymentQuerySuccess(data: CustomResponse<PreferenceResponse>['data'], dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.log(data);
    
    const preferenceResponse = data.data;

    navigate("/login");
  
}

function HandleCreatePlanPaymentQueryError(queryError: QueryError, dispatch: Dispatch<AnyAction>, navigate: NavigateFunction) {

    console.error(queryError.message);

    switch(queryError.httpStatusCode){ 

        case 400: {  //Bad Request. Parâmetros errados, então remover conta salva e reiniciar o processo
            dispatch(removeUser());
            setTimeout(()=>navigate("/signup"), 2000);
            break;
        }
        case 404:{ //Not Found. Conta do usuário não foi encontrada, então remover conta salva e reiniciar o processo
            dispatch(removeUser()); 
            setTimeout(()=>navigate("/signup"), 2000);
            break
        }
        case 502: { //Bad Gateway. Erro com a api de pagamento, não há tratamento
            break;
        }
        case 500: { //Internal Server Error. Erro em runtime desconhecido, não há tratamento
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

interface PreferenceResponse {
    response: {
        statusCode: number,
        headers: object,
        content: string
    },
    id: string,
    items: [
        {
            id: string,
            title: string,
            description: string,
            pictureUrl: null,
            categoryId: string,
            quantity: number,
            unitPrice: number,
            currencyId: string
        }
    ],
    payer: {
        name: string,
        surname: string,
        email: string,
        phone: {
            areaCode: string,
            number: string
        },
        identification: {
            type: string,
            number: string
        },
        address: {
            zipCode: string,
            streetName: string,
            streetNumber: null | string
        },
        dateCreated: null | Date,
        lastPurchase: null | Date
    },
    clientId: number,
    paymentMethods: {
        excludedPaymentMethods: object[],
        excludedPaymentTypes: object[],
        defaultPaymentMethodId: null | string,
        installments: null | number,
        defaultInstallments: null | number
    },
    backUrls: {
        success: string,
        pending: string,
        failure: string
    },
    shipments: {
        mode: null | string,
        localPickup: null | string,
        dimensions: null | string,
        defaultShippingMethod: null | string,
        freeMethods: null | string,
        cost: null | string,
        freeShipping: null | string,
        receiverAddress: {
            zipCode: string,
            streetName: string,
            streetNumber: null | string,
            countryName: null | string,
            stateName: null | string,
            floor: string,
            apartment: string,
            cityName: null | string
        },
        expressShipment: null | string
    },
    notificationUrl: null | string,
    statementDescriptor: null | string,
    externalReference: string,
    expires: boolean,
    dateOfExpiration: null,
    expirationDateFrom: null,
    expirationDateTo: null,
    collectorId: number,
    marketplace: string,
    marketplaceFee: number,
    additionalInfo: string,
    autoReturn: string,
    operationType: string,
    differentialPricing: null | number,
    processingModes: null | string,
    binaryMode: boolean,
    taxes: null | number,
    tracks: null | string,
    metadata: object,
    initPoint: string,
    sandboxInitPoint: string,
    dateCreated: Date
}
