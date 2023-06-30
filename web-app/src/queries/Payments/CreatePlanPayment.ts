import { useQuery } from 'react-query';
import axios from '../../libs/axios';

import PaymentType from '../../types/PaymentType';
import Plan from '../../types/Plan';

//import Axios from 'axios';
import { AxiosError } from 'axios';

interface CreatePlanPaymentResponse {
    message: string;
    data: PreferenceResponse | null;
}

async function createPlanPayment (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ){

    if(accountId && plan!=null && paymentType!=null){

        try{

            const response = await axios.post(
                '/payment/createPlanPayment',
                {accountId, plan, paymentType},
            );

            const data = response.data as CreatePlanPaymentResponse;
            console.log(data.message);

            return data;

        } catch (err: any) {

            //const error = err as (Error | AxiosError);
            const error = err as AxiosError;

            //if(Axios.isAxiosError(error)){
            if (error.response) {
                // The request was made and the server responded with a status code that falls out of the range of 2xx

                const data = error.response.data as CreatePlanPaymentResponse;

                switch(error.response.status){
                    case 400:
                        localStorage.removeItem("user"); //Bad Request. Parâmetros errados, então remover conta salva e reiniciar o processo
                        return data;
                    case 404:
                        localStorage.removeItem("user"); //Not Found. Conta do usuário não foi encontrada, então remover conta salva e reiniciar o processo
                        return data;
                    case 502: //Bad Gateway. Erro com a api de pagamento, nada a ser tratado
                        throw new Error(error.response.status + " - " + data.message);
                    case 500: //Internal Server Error. Erro em runtime desconhecido, nada a ser tratado
                        throw new Error(error.response.status + " - " + data.message);

                    default: //Erro não mapeado pelo controlador no backend
                        throw new Error(error.response.status + " - " + data.message);
                }

            }
            else if (error.request) {
                // The request was made but no response was received, `error.request` is an instance of XMLHttpRequest in the browser 
                throw new Error('O servidor não pode responder a essa requisição.');
            } 
            else {
                // Something happened in setting up the request that triggered an Error
                throw new Error(error.message);
            }
            //} else throw new Error(error.message); //Erro fora do axios
        }

    } else throw new Error('Error: AccountId, Plan or PaymentType are Undefined.');

}

export default function CreatePlanPaymentQuery (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ) {

    const createPlanPaymentQuery = useQuery<CreatePlanPaymentResponse, unknown>(
        'createPlanPayment',
        async () => createPlanPayment(accountId, plan, paymentType),
        {
            enabled: true,
        }
    );

    return createPlanPaymentQuery;

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
