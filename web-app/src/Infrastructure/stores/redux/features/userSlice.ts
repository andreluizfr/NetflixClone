import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';

import { User } from '@Model/entities/User';

import { ReduxAction } from './ReduxAction';

import { createSlice } from '@reduxjs/toolkit';

export interface UserState {
    data: User | null;
}

const userSlice = createSlice({
    name: 'user',
    initialState: {
        data: null
    } as UserState,
    reducers: {
        saveUser(state, action: ReduxAction<User>) {
            state.data = action.payload;

            const persistentStorage = makePersistentStorage();
            persistentStorage.set("user", action.payload);
        },
        removeUser(state){
            // eslint-disable-next-line @typescript-eslint/no-unused-vars
            state.data = null;

            const persistentStorage = makePersistentStorage();
            persistentStorage.remove("user");
            persistentStorage.remove("x-access-token");
        }
    }
});

export const { saveUser, removeUser } = userSlice.actions;
export default userSlice.reducer;