import { useQuery } from 'react-query';
import axios from '../../libs/axios';

import PaymentType from '../../types/PaymentType';
import Plan from '../../types/Plan';

import refreshToken from '../RefreshToken';

import Axios from 'axios';
import { AxiosError } from 'axios';

interface CreatePlanPaymentResponse {
    refresh: boolean;
    message: string;
    payment: object;
}

async function createPlanPayment (
    paymentForm: object | null,
    accountId: string | null,
    plan: Plan | null, 
    paymentType: PaymentType | null
    ){

    if(paymentForm && accountId && plan!=null && paymentType!=null){

        const accessToken = localStorage.getItem("x-access-token");

        try{

            const response = await axios.post(
                '/payment/createPlanPayment',
                {paymentForm, accountId, plan, paymentType},
                {headers: { Authorization: `Bearer ${accessToken}` }}
            );

            const data = response.data as CreatePlanPaymentResponse;
            console.log(data.message);

            if(data.refresh) {
                const redo = (await refreshToken()).redo;

                if(redo) return createPlanPayment(paymentForm, accountId, plan, paymentType);

                else { //redirecionar para login novamente
                    window.location.href = process.env.REACT_APP_APP_BASE_URL + "/login";
                    throw new Error("Não foi possível atualizar sua sessão, você precisará logar novamente.");
                } 
            }

            return data;

        } catch (err: any) {

            const error = err as (Error | AxiosError);

            if(Axios.isAxiosError(error)){

                if (error.response) {
                    // The request was made and the server responded with a status code that falls out of the range of 2xx 
                    console.error(error.response.status);
                    throw new Error((error.response.data as CreatePlanPaymentResponse).message);
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
    accountId: string | null,
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