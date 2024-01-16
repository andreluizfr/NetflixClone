import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';

import { User } from '@Model/entities/User';
import { Profile } from '@Model/entities/Profile';

import { ReduxAction } from './ReduxAction';

import { createSlice } from '@reduxjs/toolkit';


export interface UserState {
    data: User | null;
    selectedProfile: Profile | null;
}

const userSlice = createSlice({
    name: 'user',
    initialState: {
        data: null,
        selectedProfile: null
    } as UserState,
    reducers: {
        saveUser(state: UserState, action: ReduxAction<User>) {
            state.data = action.payload;

            const persistentStorage = makePersistentStorage();
            persistentStorage.set("user", action.payload);
        },
        removeUser(state: UserState){
            state.data = null;

            const persistentStorage = makePersistentStorage();
            persistentStorage.remove("user");
            persistentStorage.remove("x-access-token");
        },
        setProfile(state: UserState, action: ReduxAction<Profile | undefined> ){
            const persistentStorage = makePersistentStorage();

            if(action.payload === undefined){
                const selectedProfile = persistentStorage.get<Profile>("selectedProfile");
                state.selectedProfile = selectedProfile;
                persistentStorage.set("selectedProfile", selectedProfile);
            } else {
                state.selectedProfile = action.payload;
                persistentStorage.set("selectedProfile", action.payload);
            }   
        },
        removeProfile(state: UserState){
            state.selectedProfile = null;

            const persistentStorage = makePersistentStorage();
            persistentStorage.remove("selectedProfile");
        }
    }
});

export const { saveUser, removeUser, setProfile, removeProfile } = userSlice.actions;
export default userSlice.reducer;