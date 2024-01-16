import StyledInput from '@Presentation/components/StyledInput';

import { LoginService } from '@Services/LoginService';

import styles from "@Model/constants/styles.json";

import { useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import styled from 'styled-components';
import { useSelector } from 'react-redux';
import { StoreState } from '@Infrastructure/store/redux/config';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';


export default function Login(): JSX.Element{

    //  ############# Manipulação de requisição ##################
    const [email, setEmail] = useState<string | null>(null);
    const [password, setPassword] = useState<string | null>(null);

    const loginResult = LoginService(email, password);


    //  ############# Manipulação de dados na view ##################
    function onSubmit (e: React.FormEvent){
        e.preventDefault();

        loginResult.refetch();
    }


    //  ############# Renderização do conteúdo ##################
    const themeStore = useSelector((state: StoreState) => state.theme);
    //const isSmall = useMediaQuery({ query: '(max-width: 576px)' });
    const isMedium = useMediaQuery({ query: '(max-width: 720px)' });
    //const isLarge = useMediaQuery({ query: '(max-width: 956px)' });

    const StyledFormControlLabel = styled(FormControlLabel)(() => ({
        '& .MuiCheckbox-root':{
            color: themeStore.light ? styles.neutral_400 : styles.neutral_400
        },
        '& .Mui-checked': {
            color: themeStore.light ? styles.neutral_400+" !important" : styles.neutral_400+" !important"
        },
        '& .MuiFormControlLabel-label': {
            color: themeStore.light ? styles.neutral_400 : styles.neutral_400,
            fontSize: styles.font_size_small
        }
      }));

    return (
        <LoginPage $isMedium={isMedium} $light={themeStore.light}>

            <LoginForm $isMedium={isMedium} $light={themeStore.light} onSubmit={onSubmit}>
                <Title $light={themeStore.light} >Entrar</Title>

                <StyledInput 
                    title='Email ou número de telefone'
                    warning='Informe um email ou número de telefone válido.'
                    hasShow={false}
                    required
                    inputValue={email}
                    setInputValue={setEmail}
                />

                <StyledInput 
                    title='Senha' 
                    warning='A senha deve ter entre 4 e 60 caracteres.'
                    hasShow={true}
                    minLength={4}
                    maxLength={60}
                    required
                    inputValue={password}
                    setInputValue={setPassword}
                />

                <LoginButton type='submit'>
                    Entrar
                </LoginButton>

                <RememberMeAndNeedHelpContainer>
                    <StyledFormControlLabel  control={<Checkbox />} label="Lembre-se de mim" />

                    <NeedHelp $light={themeStore.light}>
                        Precisa de ajuda?
                    </NeedHelp>
                </RememberMeAndNeedHelpContainer>

                <Recaptcha $light={themeStore.light}>
                    <RecaptchaNotice>
                        Esta página é protegida pelo Google reCAPTCHA
                        para garantir que você não é um robô.
                    </RecaptchaNotice>
                    <MoreAbout> Saiba mais.</MoreAbout>
                </Recaptcha>
            </LoginForm>

        </LoginPage>
    );
}

const LoginPage = styled.main<{$isMedium: boolean, $light: boolean}>`
    flex: 1;
    width: 100%;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;


const LoginForm = styled.form<{$isMedium: boolean, $light: boolean}>`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 16px;

    width: ${props => props.$isMedium ? "100%" : "440px"};
    padding: ${props => props.$isMedium ? "48px 22px" : "60px 68px"};
    background-color: ${props => props.$light ? styles.neutral_100: styles.neutral_900};
    border-radius: 8px;
    box-shadow: ${props => props.$isMedium? "none" : "rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px"};
    //box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px; //pouquinho
    //box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset; //pra dentro
`;

const Title = styled.h1<{$light: boolean}>`
    margin: 0 0 8px 0;
    font-size: ${styles.font_size_xl};
    color: ${props => props.$light ? styles.neutral_500 : styles.neutral_100};
    font-weight: 600;
`;

const LoginButton = styled.button<{}>`
    width: 100%;
    height: 48px;
    border-radius: 4px;
    background-color: ${styles.red_primary};

    color: ${styles.neutral_100};
    font-size: ${styles.font_size_normal};
    font-weight: 600;

    cursor: pointer;
`;

const RememberMeAndNeedHelpContainer = styled.div<{}>`
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

const NeedHelp = styled.span<{$light: boolean}>`
    color: ${props => props.$light ? styles.neutral_400 : styles.neutral_400};
    font-size: ${styles.font_size_small};
    cursor: pointer;
`;

const Recaptcha = styled.div<{$light: boolean}>`
    color: ${props => props.$light ? styles.neutral_400 : styles.neutral_400};
`;

const RecaptchaNotice = styled.span<{}>`
    margin: 8px 0 0 0;
    font-size: ${styles.font_size_small};
    line-height: 15px;
`;

const MoreAbout = styled.a<{}>`
    color: ${styles.blue_primary};
    text-decoration: none;
    font-size: ${styles.font_size_small};

    &:visited{
        color: #0071eb;
        text-decoration: none;
    }
    &:hover{
        text-decoration: underline;
    }
`;