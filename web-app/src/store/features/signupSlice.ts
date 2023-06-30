import { createSlice } from '@reduxjs/toolkit';

import Plan from '../../types/Plan';
import PaymentType from '../../types/PaymentType';

interface SignupState {
    step: number
    email: string | null
    plan: Plan | null
    password: string | null
    paymentType: PaymentType | null
}

const signupSlice = createSlice({
    name: 'signup',
    initialState: {
        step: 1,
        email: null,
        plan: null,
        password: null,
        paymentType: null
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
        savePaymentType(state, action) {
            state.paymentType = action.payload;
        },
        clear(state) {
            state.step= 1;
            state.email= null;
            state.plan= null;
            state.password= null;
            state.paymentType= null;
        },
    }
});

export const { setStep, saveEmail, setPlan, savePassword, savePaymentType, clear } = signupSlice.actions;
export default signupSlice.reducer;