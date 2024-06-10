import axios from "axios";
import { makePersistentStorage } from "@Main/factories/infrastructure/makePersistentStorage";
import store from "@Infrastructure/store/redux/config";
import { removeUser } from '@Infrastructure/store/redux/features/userSlice';
import { HttpStatusCode } from "../HttpStatusCode";

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL
});

axiosInstance.defaults.headers.common['Access-Control-Allow-Origin'] = import.meta.env.VITE_APP_BASE_URL;
axiosInstance.defaults.headers.common['Access-Control-Allow-Methods'] = "POST, GET, PUT, DELETE, OPTIONS";
axiosInstance.defaults.headers.common['Access-Control-Allow-Headers'] = "*";
axiosInstance.defaults.headers.common['Access-Control-Expose-Headers'] = "Set-Cookie";
axiosInstance.defaults.withCredentials = true; 

const persistentStorage = makePersistentStorage();

axiosInstance.interceptors.request.use(requestConfig => {

    const accessToken = persistentStorage.get<string>("x-access-token");

    if(accessToken) {
        requestConfig.headers.Authorization = `Bearer ${accessToken}`;
    }

    return requestConfig;
});

axiosInstance.interceptors.response.use(
    response => response, 
    error => {
        if(error.response && error.response.status == HttpStatusCode.Forbidden) {
            store.dispatch(removeUser());
            window.location.href = import.meta.env.VITE_APP_BASE_URL;
        }
        return Promise.reject(error);
    }
);

export { axiosInstance };