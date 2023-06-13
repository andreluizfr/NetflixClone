import { createSlice } from '@reduxjs/toolkit';

interface SignupState {
    step: number
    email: string | null
}

const signupSlice = createSlice({
    name: 'signup',
    initialState: {
        step: 1,
        email: null
    } as SignupState,
    reducers: {
        setStep(state, action) {
            if(action.payload)
                state.step = action.payload;
        },
        saveEmail(state, action) {
            state.email = action.payload;
        }
    }
});

export const { setStep, saveEmail } = signupSlice.actions;
export default signupSlice.reducer;