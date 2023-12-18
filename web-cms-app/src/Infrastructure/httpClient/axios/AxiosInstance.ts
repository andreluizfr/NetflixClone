import axios from "axios";

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL
});

axiosInstance.defaults.headers.common['Access-Control-Allow-Origin'] = import.meta.env.VITE_APP_BASE_URL;
axiosInstance.defaults.headers.common['Access-Control-Allow-Methods'] = "POST, GET, PUT, DELETE, OPTIONS";
axiosInstance.defaults.headers.common['Access-Control-Allow-Headers'] = "Content-Type, Accept, Authorization, Access-control-allow-methods","Access-Control-Allow-Origin", "Access-control-allow-headers";
axiosInstance.defaults.withCredentials = true; 

export { axiosInstance };