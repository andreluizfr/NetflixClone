import { Dispatch, SetStateAction, useCallback, useEffect, useState } from "react";

export const useLocalStorage = (key: string, initialValue: string) => {

    const readValue = useCallback(() => {
        if (typeof window === 'undefined') 
            return initialValue;
        try {
            const item = window.localStorage.getItem(key)
            return item?item:initialValue;
        } catch (error) {
            console.warn(`Error reading localStorage key “${key}”:`, error);
            return initialValue;
        }
    }, [initialValue, key]);

    const [data, setData] = useState(readValue);

    useEffect(()=>{
        if(data){
            if (typeof window === 'undefined')
                console.warn(`Tried setting localStorage key “${key}” even though environment is not a client`);
            try{
                window.localStorage.setItem(key, data);
            } 
            catch (error) {
                console.warn(`Error setting localStorage key “${key}”:`, error)
            }
        }
    }, [data]);

    return [data, setData] as [string, Dispatch<SetStateAction<string>>];

} 