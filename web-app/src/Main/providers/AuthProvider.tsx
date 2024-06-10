import { FetchUserService } from "@Services/FetchUser/FetchUserService";
import Loading from "@Presentation/pages/Client/Loading";

import React from "react";

interface AuthProviderProps extends React.HTMLAttributes<HTMLDivElement>{

}

export function AuthProvider({children}:AuthProviderProps): JSX.Element {

    const fetchUserResult = FetchUserService();

    if(fetchUserResult.isLoading || fetchUserResult.isFetching) return <Loading/>;

    return <>{children}</>
}