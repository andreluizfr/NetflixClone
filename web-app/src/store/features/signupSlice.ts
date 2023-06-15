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
    password: string | null
    payment: string | null
    birthdayDate: Date | null
}

const signupSlice = createSlice({
    name: 'signup',
    initialState: {
        step: 1,
        email: null,
        plan: null,
        password: null,
        payment: null,
        birthdayDate: null
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
        },
        savePassword(state, action) {
            state.password = action.payload;
        },
        savePayment(state, action) {
            state.payment = action.payload;
        },
        saveBirthdayDate(state, action) {
            state.birthdayDate = action.payload;
        },
    }
});

export const { setStep, saveEmail, setPlan, savePassword, savePayment, saveBirthdayDate } = signupSlice.actions;
export default signupSlice.reducer;