import { Plan } from '@Model/entities/Account';
import { PaymentType } from '@Model/types/PaymentType';

import { ReduxAction } from './ReduxAction';

import { createSlice } from '@reduxjs/toolkit';


export interface SignupDataState {
    step: number
    email: string | null
    plan: Plan | null
    password: string | null
    paymentType: PaymentType | null
}

const signupDataSlice = createSlice({
    name: 'signupData',
    initialState: {
        step: 1,
        email: null,
        plan: null,
        password: null,
        paymentType: null
    } as SignupDataState,
    reducers: {
        saveStep(state, action: ReduxAction<number>) {
            if(action.payload)
                state.step = action.payload;
        },
        saveEmail(state, action: ReduxAction<string>) {
            state.email = action.payload;
        },
        savePlan(state, action) {
            state.plan = action.payload;
        },
        savePassword(state, action: ReduxAction<string>) {
            state.password = action.payload;
        },
        savePaymentType(state, action: ReduxAction<PaymentType>) {
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

export const { saveStep, saveEmail, savePlan, savePassword, savePaymentType, clear } = signupDataSlice.actions;
export default signupDataSlice.reducer;