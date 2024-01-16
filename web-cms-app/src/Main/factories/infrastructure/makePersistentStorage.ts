import { IPersistentStorage } from "@Infrastructure/persistentStorage/IPersistentStorage";

import { LocalStorageImpl } from "@Infrastructure/persistentStorage/localStorage/persistentStorageImpl";

//Factory method pattern
export function makePersistentStorage(): IPersistentStorage {

    const persistentStorage: IPersistentStorage = new LocalStorageImpl();

    return persistentStorage;
}