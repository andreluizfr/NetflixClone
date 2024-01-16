import { StoreState } from '@Infrastructure/store/redux/config';
import styles from "@Model/constants/styles.json";

import CircularProgress from '@mui/material/CircularProgress';
import { styled } from '@mui/material/styles';

import { useSelector } from 'react-redux';
import styledComponent from 'styled-components';


export default function Loading() : JSX.Element {
    
    const languageStore = useSelector((state: StoreState) => state.language);
    const themeStore = useSelector((state: StoreState) => state.theme);

    const StyledCircularProgress = styled(CircularProgress)(() => ({
        color: styles.red_primary
    }));

    //  ############# Renderização do conteúdo ##################
    return(
        <LoadingPage>
            <StyledCircularProgress 
                size={40}
            />
            <LoadingText $light={themeStore.light}>{languageStore.messages.loading}</LoadingText>
        </LoadingPage>
    );
}

const LoadingPage = styledComponent.main<{}>`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    min-width: 100%;
    min-height: 100vh; 
    background: ${styles.neutral_700};
    gap: 4px;
`;

const LoadingText = styledComponent.p<{$light: boolean}>`
    font-weight: 400;
    font-size: ${styles.font_size_medium};
    color: ${props => props.$light ? styles.neutral_900 : styles.neutral_100 };
`;