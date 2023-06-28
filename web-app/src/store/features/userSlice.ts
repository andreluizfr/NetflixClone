import { createSlice } from '@reduxjs/toolkit';
import { User } from '../../types/User';

type UserState = User | null;

const userSlice = createSlice({
    name: 'user',
    initialState: null as UserState,
    reducers: {
        addUser(state, action) {
            state = action.payload as User;
        },
        removeUser(state){
            state = null;
        }
    }
});

export const { addUser, removeUser } = userSlice.actions;
export default userSlice.reducer;