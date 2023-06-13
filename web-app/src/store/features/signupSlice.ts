import { createSlice } from '@reduxjs/toolkit';


enum Plan{
    DefaultWithAds,
    Default,
    Premium
}

interface SignupState {
    step: number
    email: string | null
    plan: Plan | null
}

const signupSlice = createSlice({
    name: 'signup',
    initialState: {
        step: 1,
        email: null,
        plan: null
    } as SignupState,
    reducers: {
        setStep(state, action) {
            if(action.payload)
                state.step = action.payload;
        },
        saveEmail(state, action) {
            state.email = action.payload;
        },
        setPlan(state, action) {
            state.plan = action.payload;
        }
    }
});

export const { setStep, saveEmail, setPlan } = signupSlice.actions;
export default signupSlice.reducer;