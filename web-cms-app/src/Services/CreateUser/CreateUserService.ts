import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { User } from "@Model/entities/User";

import { useMutation } from "@tanstack/react-query";

export const CreateUserService = (
    email: string | null,
    password: string | null,
    birthDate: Date | null
) => {

    if(!email || !password || !birthDate)
        throw new Error("Erro: Email, senha ou data de aniversário não foram identificados.");

    const mutationResult = useMutation<IHttpResponse<User>, IHttpError, unknown, unknown>(
        ['createUser'],
        async () => CreateUserHttpRequest(email, password, birthDate),
        {
            onSettled: async (data, error) => {
                console.log({data, error});
            }
        }
    );

    return mutationResult; //para fazer o devido uso com relação a camada de view do react
}

async function CreateUserHttpRequest (
    email: string,
    password: string,
    birthDate: Date, 
): Promise<IHttpResponse<User>>{

    const httpClient = makeHttpClient<User>();

    const httpResponse = httpClient.post(
        '/user/create',
        {email, password, birthDate},
    )

    return httpResponse;
}