import { createSlice } from '@reduxjs/toolkit';
import labels from '@Model/constants/labels.json';
import messages from '@Model/constants/messages.json';
import { makePersistentStorage } from '@Main/factories/infrastructure/makePersistentStorage';
import { ReduxAction } from './ReduxAction';

export interface LanguageState {
    selectedLanguage: string;
    labels: object;
    messages: object;
}

const persistentStorage = makePersistentStorage();

const languageSlice = createSlice({
    name: 'language',
    initialState: {
        selectedLanguage: persistentStorage.get<string>("selectedLanguage")?
            persistentStorage.get<string>("selectedLanguage") : "pt-BR",
        labels: persistentStorage.get<string>("selectedLanguage") === "en-US"? 
            labels.en : labels.pt,
        messages: persistentStorage.get<string>("selectedLanguage") === "en-US"? 
            messages.en : messages.pt,
    } as LanguageState,
    reducers: {
        selectLanguage(state: LanguageState, action: ReduxAction<string>) {
            if(action.payload){
                state.selectedLanguage = action.payload;
                persistentStorage.set("selectedLanguage", action.payload);

                if(action.payload === "pt-BR")
                    state.labels = labels.pt;
                else if(action.payload === "en-US")
                    state.labels = labels.en;

                if(action.payload === "pt-BR")
                    state.messages = messages.pt;
                else if(action.payload === "en-US")
                    state.messages = messages.en;
            }
        }
    }
});

export const { selectLanguage } = languageSlice.actions;
export default languageSlice.reducer;