import { QueryClient } from 'react-query';

export interface QueryError {
    httpStatusCode: number | null;
    message: string;
}

export const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            enabled: false,
            retry: false,
            staleTime: 0,
            cacheTime: 0,
        },
    },
});