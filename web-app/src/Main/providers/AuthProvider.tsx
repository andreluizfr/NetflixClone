import { FetchUserService } from "@Application/useCases/FetchUser/FetchUserService";

import React from "react";

interface AuthProviderProps extends React.HTMLAttributes<HTMLDivElement>{

}

export function AuthProvider({children}:AuthProviderProps): JSX.Element {

    FetchUserService();

    return  <>{children}</>
}