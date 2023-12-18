import { QueryClientProvider, QueryClient } from "@tanstack/react-query";
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

interface ProviderProps {
    children?: React.ReactNode,
}

export const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            refetchOnWindowFocus: false,
            enabled: false,
            retry: false,
            staleTime: 0,
            cacheTime: 6 * 60 * 60 * 1000,
        },
        mutations: {
            onSettled: async (data, error) => {
                console.log({data, error});
            }
        }
    },
});

export const ReactQueryProvider = ({children}: ProviderProps) => {
    return (
        <QueryClientProvider client={queryClient}>
            {children}
            {import.meta.env.DEV && 
                <ReactQueryDevtools initialIsOpen={false} />
            }
        </QueryClientProvider>
    );
}