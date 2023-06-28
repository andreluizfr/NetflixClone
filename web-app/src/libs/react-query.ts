import { QueryClient } from 'react-query';

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