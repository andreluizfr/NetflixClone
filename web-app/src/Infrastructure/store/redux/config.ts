import { configureStore } from '@reduxjs/toolkit';
import userReducer from './features/userSlice';
import signupReducer from './features/signupDataSlice';
import languageReducer from './features/languageSlice';
import themeReducer from './features/themeSlice';

const store = configureStore({
    reducer: {
      user: userReducer,
      signup: signupReducer,
      language: languageReducer,
      theme: themeReducer
    }
});
  
export default store;
  
export type StoreState = ReturnType<typeof store.getState>;
  
export type StoreDispatch = typeof store.dispatch;