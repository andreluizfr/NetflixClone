import { IPersistentStorage } from "@Application/interfaces/persistentStorage/IPersistentStorage";

import { LocalStorageImpl } from "@Infrastructure/persistentStorages/localStorage/persistentStorageImpl";

//Factory method pattern
export function makePersistentStorage(): IPersistentStorage {

    const persistentStorage: IPersistentStorage = new LocalStorageImpl();

    return persistentStorage;
}