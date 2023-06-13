import { createSlice } from '@reduxjs/toolkit';
import { User } from '../../types/User';

interface UserState {
    value: User | null
}

const userSlice = createSlice({
    name: 'user',
    initialState: {
        value: null
    } as UserState,
    reducers: {
        addUser(state, action) {
            state.value = action.payload as User;
        },
        removeUser(state){
            state.value = null;
        }
    }
});

export const { addUser, removeUser } = userSlice.actions;
export default userSlice.reducer;