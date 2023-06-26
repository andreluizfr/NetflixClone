import { useQuery } from 'react-query';
import axios from '../../libs/axios';

import PaymentType from '../../types/PaymentType';
import Plan from '../../types/Plan';

import Axios from 'axios';
import { AxiosError } from 'axios';

interface CreatePlanPaymentResponse {
    message: string;
    payment: object;
}

async function createPlanPayment (
    paymentForm: object | null,
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ){

    if(paymentForm && accountId && plan!=null && paymentType!=null){

        try{

            const response = await axios.post(
                '/payment/createPlanPayment',
                {paymentForm, accountId, plan, paymentType},
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

    } else throw new Error('Error: Form or accountId are Undefined.');

}

export default function CreatePlanPaymentQuery (
    paymentForm: object | null,
    accountId: string | undefined,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ) {

    const createPlanPaymentQuery = useQuery<CreatePlanPaymentResponse, unknown>(
        'createPlanPayment',
        async () => createPlanPayment(paymentForm, accountId, plan, paymentType),
        {
            refetchOnWindowFocus: false,
            enabled: false
        }
    );

    return createPlanPaymentQuery;

}