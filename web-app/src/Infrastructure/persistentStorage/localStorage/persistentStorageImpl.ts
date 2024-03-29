import { IPersistentStorage } from "@Infrastructure/persistentStorage/IPersistentStorage";

export class LocalStorageImpl implements IPersistentStorage {

    get<T>(key: string): T | null {

        const value = localStorage.getItem(key);

        if(value != null)
            return JSON.parse(value) as T;
        else
            return null;
    }

    set(key: string, value: any): void {

        localStorage.setItem(key, JSON.stringify(value));
    }

    remove(key: string): void {
        
        localStorage.removeItem(key);
    }

    clearAll(): void {
        
        localStorage.clear();
    }

}