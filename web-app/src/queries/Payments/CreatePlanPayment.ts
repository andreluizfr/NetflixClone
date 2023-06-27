import { useQuery } from 'react-query';
import axios from '../../libs/axios';

import PaymentType from '../../types/PaymentType';
import Plan from '../../types/Plan';

import Axios from 'axios';
import { AxiosError } from 'axios';

interface CreatePlanPaymentResponse {
    message: string;
    data: PreferenceResponse;
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

            const error = err as (Error | AxiosError);

            if(Axios.isAxiosError(error)){

                if (error.response) {
                    // The request was made and the server responded with a status code that falls out of the range of 2xx 
                    throw new Error(error.response.status + " - " +(error.response.data as CreatePlanPaymentResponse).message);
                }
                else if (error.request) {
                    // The request was made but no response was received, `error.request` is an instance of XMLHttpRequest in the browser 
                    throw new Error('O servidor não pode responder a essa requisição.');
                } 
                else {
                    // Something happened in setting up the request that triggered an Error
                    throw new Error(error.message);
                }

            } else throw new Error(error.message); //Erro fora do axios

        }

    } else return undefined;

}

export default function CreatePlanPaymentQuery (
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ) {

    const createPlanPaymentQuery = useQuery<CreatePlanPaymentResponse | undefined, unknown>(
        'createPlanPayment',
        async () => createPlanPayment(accountId, plan, paymentType),
        {
            refetchOnWindowFocus: false,
            enabled: true
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
