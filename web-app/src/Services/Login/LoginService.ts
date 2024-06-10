import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";
import { queryClient } from "@Main/providers/ReactQueryProvider";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { useMutation } from "@tanstack/react-query";
import { useEffect } from "react";
import { useNavigate } from 'react-router-dom';

import { GetMediaListsHttpRequest } from "../GetMediaLists/GetMediaListsService";

interface LoginServiceProps {
    email: string,
    password: string
};

export const LoginService = () => {

    const queryResult = useMutation<IHttpResponse<string>, IHttpError, LoginServiceProps>(
        ['login'],
        async ({email, password}: LoginServiceProps) => LoginHttpRequest(email, password)
    );
    
    const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.data?.data) {
            const accessToken = queryResult.data.data;

            if(accessToken){
                const persistentStorage = makePersistentStorage();
                persistentStorage.set("x-access-token", accessToken);

                setTimeout(()=>navigate("/whoIsWatching", { replace: true }), 2000);

                queryClient.prefetchQuery({queryKey: ['getMediaLists'], queryFn: GetMediaListsHttpRequest });
            }
        }
    }, [queryResult.data]);

    return queryResult;
}

async function LoginHttpRequest (
    email: string,
    password: string,
): Promise<IHttpResponse<string>>{
    
    if(!email || !password)
        throw {
            httpStatusCode: null,
            message: 'Erro: Email ou senha não foram identificados.'
        };

    const httpClient = makeHttpClient<string>();

    const httpResponse = httpClient.post(
        '/auth/login',
        {email, password}
    );

    return httpResponse;  
}