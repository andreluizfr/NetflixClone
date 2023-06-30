import { useQuery } from 'react-query';
import axios from '../../libs/axios';
axios.defaults.withCredentials = false; 

import { AxiosError } from 'axios';
import { User } from '../../types/User';
//import { useNavigate } from 'react-router-dom';

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

        //const navigate = useNavigate();
        
        try{

            const response = await axios.post(
                '/user/create',
                {email, password, birthDate},
            );

            const data = response.data as CreateUserResponse;
            console.log(data);
            
            return data;

        } catch (err: any) {

            const error = err as AxiosError;

            if (error.response) {
                // The request was made and the server responded with a status code that falls out of the range of 2xx 
                
                const data = error.response.data as CreateUserResponse;

                switch(error.response.status){ 
                    case 400: { //Bad Request. Erro nos parametros passados
                        setTimeout(()=>window.location.href = import.meta.env.BASE_URL + "/signup", 3000);
                        throw new Error(error.response.status + " - " + data.message);
                    }
                    case 500: //Internal server error. erro durante a criaçao das entidades, nada a ser tratado
                        throw new Error(error.response.status + " - " + data.message);

                    default: //erro não mapeado pelo controlador do backend, nada a ser tratado
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
    );

    return createUserQuery;

}