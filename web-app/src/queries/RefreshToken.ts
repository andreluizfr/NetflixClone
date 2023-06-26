import axios from '../libs/axios';

interface RefreshTokenResponse {
    accessToken: string | null;
    message: string;
}

export default async function refreshToken () {

    const response = await axios.get('/auth/refreshToken');

    const data = response.data as RefreshTokenResponse;
    console.log(data.message);

    if(data.accessToken){
        
        localStorage.setItem("x-access-token", data.accessToken);
        return {redo: true};

    }else {

        localStorage.removeItem("x-access-token");
        return {redo: false};
    }

}