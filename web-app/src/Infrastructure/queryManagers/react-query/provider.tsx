import { QueryClientProvider, QueryClient } from "react-query";

interface ProviderProps {
    children?: React.ReactNode,
}

const queryClient = new QueryClient({
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

export const ReactQueryProvider = ({children}: ProviderProps) => {
    return (
        <QueryClientProvider client={queryClient}>
            {children}
        </QueryClientProvider>
    );
}