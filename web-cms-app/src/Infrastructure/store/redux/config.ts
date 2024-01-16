import { configureStore } from '@reduxjs/toolkit';
import userReducer from './features/userSlice';
import languageReducer from './features/languageSlice';
import themeReducer from './features/themeSlice';

const store = configureStore({
    reducer: {
      user: userReducer,
      language: languageReducer,
      theme: themeReducer
    }
});
  
export default store;
  
export type StoreState = ReturnType<typeof store.getState>;
  
export type StoreDispatch = typeof store.dispatch;