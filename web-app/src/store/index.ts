import { configureStore } from '@reduxjs/toolkit';
import userReducer from './features/userSlice';
import signupReducer from './features/signupSlice';

const store = configureStore({
  reducer: {
    user: userReducer,
    signup: signupReducer
  }
});

export default store;

export type StoreState = ReturnType<typeof store.getState>;

export type StoreDispatch = typeof store.dispatch;