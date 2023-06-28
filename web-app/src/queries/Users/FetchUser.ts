import { useQuery } from 'react-query';
import axios from '../../libs/axios';
import { AxiosError } from 'axios';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addUser, removeUser } from '../../store/features/userSlice';
import { User } from '../../types/User';

interface FetchUserResponse {
    message: string;
    data: User | null;
}

async function fetchUser (){

    const accessToken = localStorage.getItem("x-access-token");

    if(accessToken){

        const navigate = useNavigate();
        const dispatch = useDispatch();

        try{

            const response = await axios.get(
                '/user/fetchUser', 
                {headers: { Authorization: `Bearer ${accessToken}` }}
            );

            const data = response.data as FetchUserResponse;

            dispatch(addUser(data));
            
            return data;

        } catch (err: any) {

            const error = err as AxiosError;

            if (error.response) {
                // The request was made and the server responded with a status code that falls out of the range of 2xx

                const data = error.response.data as FetchUserResponse;

                switch(error.response.status){ 
                    case 401: { //Unauthorized. Não autenticado, token inválido
                        localStorage.removeItem("x-access-token");
                        dispatch(removeUser());

                        setTimeout(()=>navigate("/"), 3000);
                        return data;
                    }
                    case 403: { //Forbidden. token válido mas expirado
                        localStorage.removeItem("x-access-token"); 
                        //chamar função de refresh, inves de fazer essas linhas abaixo
                        dispatch(removeUser());

                        setTimeout(()=>navigate("/"), 3000);
                        return data;
                    }
                    case 500: //Internal server error. erro em runtime desconhecido, nada a ser tratado
                        return {
                            message: "Error: " + error.response.status + " - " + data.message,
                            data: null
                        } as FetchUserResponse;

                    default: //erro não mapeado pelo controlador do backend
                        return {
                            message: "Error: " + error.response.status + " - " + data.message,
                            data: null
                        } as FetchUserResponse;
                }

            }
            else if (error.request) {
                // The request was made but no response was received, `error.request` is an instance of XMLHttpRequest in the browser 
                throw new Error(" O servidor não pode responder a essa requisição.");
            } 
            else {
                // Something happened in setting up the request that triggered an Error
                throw new Error(error.message);
            }

        }

    } else return {
            message: "Error: AccessToken not found.",
            data: null
        } as FetchUserResponse;

}

export default function FetchUserQuery () {

    const fetchUserQuery = useQuery<FetchUserResponse | null, unknown>(
        'fetchUser',
        async () => fetchUser(),
        {
            enabled: true,
            staleTime: 30 * 1000, //30s. Until 30s it will not make the Request. When the 30s pass it uses cache as fallback while loading the query
            cacheTime: 60 * 60 * 1000, //after 1 hour, the cache it will be invalid and it will no be used as fallback if the data is stale. It has to be greater than staleTime
            initialData: ()=>{
                let data;
                const user = localStorage.getItem("user");

                if(user) data = JSON.parse(user) as User;
                else data = null;

                return {
                    message: "fetching storaged user data", 
                    data: data
                };
            },
        }
    );

    return fetchUserQuery;

}