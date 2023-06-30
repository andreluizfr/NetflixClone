import { createSlice } from '@reduxjs/toolkit';
import { User } from '../../types/User';

type UserState = {
    data: User | null;
};

const userSlice = createSlice({
    name: 'user',
    initialState: {
        data: null
    } as UserState,
    reducers: {
        addUser(state, action) {
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

export const { addUser, removeUser } = userSlice.actions;
export default userSlice.reducer;