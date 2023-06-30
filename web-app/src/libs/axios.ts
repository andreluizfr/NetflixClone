import axios, { AxiosResponse } from 'axios';

const instance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL
});

instance.defaults.headers.common['Access-Control-Allow-Origin'] = import.meta.env.VITE_APP_BASE_URL;
instance.defaults.headers.common['Access-Control-Allow-Methods'] = "POST, GET, PUT, DELETE, OPTIONS";
instance.defaults.headers.common['Access-Control-Allow-Headers'] = "Content-Type, Accept, Authorization, Access-control-allow-methods","Access-Control-Allow-Origin", "Access-control-allow-headers";
instance.defaults.withCredentials = true; 

export interface CustomResponse<T> extends AxiosResponse {
    data: {
        message: string,
        data: T | null
    }
}

export default instance;

