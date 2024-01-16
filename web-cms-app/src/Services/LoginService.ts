import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";
import { queryClient } from "@Main/providers/ReactQueryProvider";
import { generateErrorMessage } from "@Main/utils/AuxiliaryFunctions";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";


import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";
import { NavigateFunction, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

import { FetchUserHttpRequest } from "./FetchUserService";

export const LoginService = (
    email: string | null,
    password: string | null
) => {

    const queryResult = useQuery<IHttpResponse<string>, IHttpError>(
        ['login'],
        async () => LoginHttpRequest(email, password)
    );
    
    const navigate = useNavigate();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) {
            HandleLoginQueryError(queryResult.error);
            toast.error(generateErrorMessage(queryResult.error.httpStatusCode, queryResult.error.message), {
                position: "top-center",
                hideProgressBar: true
            });
        }
        else if (queryResult.data?.data) {
            HandleLoginQuerySuccess(queryResult.data, navigate);
            toast.success("Login realizado com sucesso, você será redirecionado em breve.", {
                position: "top-right"
            });
        }
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult;
}

async function LoginHttpRequest (
    email: string | null,
    password: string | null,
): Promise<IHttpResponse<string>>{

    const httpClient = makeHttpClient<string>();

    const httpResponse = httpClient.post(
        '/auth/login',
        {email, password}
    );

    return httpResponse;  
}

function HandleLoginQuerySuccess(data: IHttpResponse<string>, navigate: NavigateFunction) {

    console.log(data);

    const accessToken = data.data;

    if(accessToken){
        const persistentStorage = makePersistentStorage();

        persistentStorage.set("x-access-token", accessToken);

        setTimeout(()=>navigate("/cms", { replace: true }), 2000);

        queryClient.prefetchQuery({ queryKey: ['fetchUser'], queryFn: () => FetchUserHttpRequest() });
    }
}

function HandleLoginQueryError(httpError: IHttpError) {

    console.error(httpError);
}