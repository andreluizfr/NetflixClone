import { FetchUserService } from "@Services/FetchUserService";
import Loading from "@Presentation/pages/LoadingPage";

import React, { useEffect } from "react";

import { useNavigate } from "react-router-dom";

interface AuthProviderProps extends React.HTMLAttributes<HTMLDivElement>{

}

export function AuthProvider({children}:AuthProviderProps): JSX.Element {
    
    const navigate = useNavigate();

    try {
        const fetchUserResult = FetchUserService();

        if(fetchUserResult.isLoading || fetchUserResult.isFetching) return <Loading/>;

        return <>{children}</>

    } catch (error: unknown) {

        useEffect(()=>{
            navigate('/login');
        }, []);
        
        return <div>
                    <p className="text-white">Você não tem permissão pra acessar essa página</p>
                    <p className="text-white">{(error as Error).message}</p>
                </div>
    }
}