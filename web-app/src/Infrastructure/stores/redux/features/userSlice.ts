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
            localStorage.setItem("user", JSON.stringify(action.payload as User));
        },
        removeUser(state){
            // eslint-disable-next-line @typescript-eslint/no-unused-vars
            state.data = null;
            localStorage.removeItem("user");
            localStorage.removeItem("x-access-token");
        }
    }
});

export const { saveUser, removeUser } = userSlice.actions;
export default userSlice.reducer;