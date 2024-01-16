import { makeHttpClient } from "@Main/factories/infrastructure/makeHttpClient";

import { IHttpError } from "@Infrastructure/httpClient/IHttpError";
import { IHttpResponse } from "@Infrastructure/httpClient/IHttpResponse";

import { Movie } from "@Model/entities/Movie";

import { useMutation } from "@tanstack/react-query";
import { queryClient } from "@Main/providers/ReactQueryProvider";
import { Media } from "@Model/entities/Media";

export const AddMovieService = (
    movie: Movie
) => {

    if(!movie)
        throw new Error("Erro: tentando adicionar um filme nulo.");

    const mutationResult = useMutation<IHttpResponse<Movie>, IHttpError, unknown, Media> (
        ['addMovie'],
        async () => AddMovieHttpRequest(movie),
        {
            onMutate: async () => {

                await queryClient.cancelQueries(['getMedias']);

                const optimisticMovie = movie;

                queryClient.setQueryData(['getMedias'], (oldList: Media[] | undefined) => 
                    oldList ? [...oldList, optimisticMovie as Media] : undefined
                );

                return optimisticMovie;
            },
            onSuccess: (data, _variables, context) => {
                queryClient.setQueryData(['getMedias'], (oldList: Media[] | undefined) =>  
                    oldList ? 
                        oldList.map(media => media.id === context?.id ?
                            (data.data as Media) 
                            : media
                        )
                        : undefined
                );
            },
            onError(_error, _variables, context) {
                queryClient.setQueryData(['getMedias'], (oldList: Media[] | undefined) => 
                    oldList ? 
                        oldList.filter(media => media.id !== context?.id) 
                        : undefined
                );
            }
        }
    );

    return mutationResult; //para fazer o devido uso com relação a camada de view do react
}

async function AddMovieHttpRequest (
    movie: Movie
): Promise<IHttpResponse<Movie>>{

    const httpClient = makeHttpClient<Movie>();

    const httpResponse = httpClient.post(
        '/media/movie/add',
        {movie},
    );

    return httpResponse;
}