import axios from 'axios';

const instance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL
});

instance.defaults.headers.common['Access-Control-Allow-Origin'] = import.meta.env.BASE_URL; 
instance.defaults.withCredentials = true;   // serve para poder enviar os tokens no header

export default instance;