import { useQuery } from 'react-query';
import axios from '../../libs/axios';

import Axios from 'axios';
import { AxiosError } from 'axios';
import { User } from '../../types/User';

interface CreateUserResponse {
    message: string;
    data: User;
}

async function createUser (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
    ){

    if(email && password && birthDate){

        try{

            const response = await axios.post(
                '/user/create',
                {email, password, birthDate},
            );

            const data = response.data as CreateUserResponse;
            console.log(data);
            
            return data;

        } catch (err: any) {

            const error = err as (Error | AxiosError);

            if(Axios.isAxiosError(error)){

                if (error.response) {
                    // The request was made and the server responded with a status code that falls out of the range of 2xx 
                    throw new Error(error.response.status + " - " + (error.response.data as CreateUserResponse).message);
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

    } else throw new Error('Error: Email, Password or Birthday are Undefined.');

}

export default function CreateUserQuery (
    email: string | null,
    password: string | null,
    birthDate: Date | null, 
    ) {

    const createUserQuery = useQuery<CreateUserResponse, unknown>(
        'createUser',
        async () => createUser(email, password, birthDate),
        {
            refetchOnWindowFocus: false,
            enabled: false,
        }
    );

    return createUserQuery;

}