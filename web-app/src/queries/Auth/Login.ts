import { useQuery } from 'react-query';
import axios from '../../libs/axios';
import Axios from 'axios';
import { AxiosError } from 'axios';

interface LoginResponse {
    message: string;
    data: string; //accessToken
}

async function login (
    email: string | null,
    password: string | null,
    ){

    if(email && password){

        try{

            const response = await axios.post(
                '/auth/login',
                {email, password},
            );

            const data = response.data as LoginResponse;
            console.log(data);
            
            return data;

        } catch (err: any) {

            const error = err as (Error | AxiosError);

            if(Axios.isAxiosError(error)){

                if (error.response) {
                    // The request was made and the server responded with a status code that falls out of the range of 2xx 
                    throw new Error(error.response.status + " - " + (error.response.data as LoginResponse).message);
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

    } else throw new Error('Error: Email or Password are Undefined.');

}

export default function LoginQuery (
    email: string | null,
    password: string | null,
    ) {

    const loginQuery = useQuery<LoginResponse, unknown>(
        'login',
        async () => login(email, password),
        {
            refetchOnWindowFocus: false,
            enabled: false,
            retry: false
        }
    );

    return loginQuery;

}