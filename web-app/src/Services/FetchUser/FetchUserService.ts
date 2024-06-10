import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { User } from "@Model/entities/User";

import { saveUser } from '@Infrastructure/store/redux/features/userSlice';

import { useQuery } from "@tanstack/react-query";
import { useEffect } from "react";
import { useDispatch } from "react-redux";

export const FetchUserService = () => {

    const queryResult = useQuery<IHttpResponse<User>, IHttpError>(
        ['fetchUser'],
        async () => FetchUserHttpRequest(),
        {
            staleTime: 5 * 1000,
            cacheTime: 60 * 60 * 1000,
            initialData: ()=>{
                const persistentStorage = makePersistentStorage();
                const user = persistentStorage.get<User>("user");

                if(user)
                    return {
                        message: "Usuário dado pelo storage", 
                        data: user
                    };
                else return undefined;
            }
        }
    );
    
    const dispatch = useDispatch();

    useEffect(()=>{
        if (queryResult.isError && queryResult.error) {
            console.error(queryResult.error);
        } else if (queryResult.data?.data) {
            dispatch(saveUser(queryResult.data.data));
        }
    }, [queryResult.isError, queryResult.error, queryResult.data]);

    return queryResult;
}

export async function FetchUserHttpRequest (){

    const httpClient = makeHttpClient<User>();

    const httpResponse = httpClient.get(
        '/user/fetch'
    );

    return httpResponse;
}
