import logo from '@Presentation/assets/svg/logo.svg';
import LanguageSelect from '@Presentation/components/LanguageSelect';
import ThemeSwitch from '@Presentation/components/ThemeSwitch';

import styles from "@Model/constants/styles.json";

import { useMediaQuery } from 'react-responsive';
import styled from 'styled-components';
import { useSelector } from 'react-redux';
import { StoreState } from '@Infrastructure/store/redux/config';


interface LayoutProps extends React.HTMLAttributes<HTMLDivElement>{

}

export default function LayoutPage({children}:LayoutProps): JSX.Element{

    //  ############# Renderização do conteúdo ##################
    const themeStore = useSelector((state: StoreState) => state.theme);
    //const isSmall = useMediaQuery({ query: '(max-width: 576px)' });
    const isMedium = useMediaQuery({ query: '(max-width: 720px)' });
    //const isLarge = useMediaQuery({ query: '(max-width: 956px)' });

    return (
        <Layout $isMedium={isMedium} $light={themeStore.light}>

            <Header>
                <Logo src={logo} alt="Netflix Logo"/>
            </Header>

            {children}

            <Footer $isMedium={isMedium} $light={themeStore.light}>
                <Row1 $light={themeStore.light}>
                    Dúvidas? Ligue <NumberLink href="#" $light={themeStore.light}>0800 591 8942</NumberLink>
                </Row1>

                <Row2 $light={themeStore.light}>
                    <Row2Link $light={themeStore.light} href="#">Perguntas frequentes</Row2Link>
                    <Row2Link $light={themeStore.light} href="#">Central de Ajuda</Row2Link>
                    <Row2Link $light={themeStore.light} href="#">Termos de Uso</Row2Link>
                    <Row2Link $light={themeStore.light} href="#">Privacidade</Row2Link>
                    <Row2Link $light={themeStore.light} href="#">Preferências de cookies</Row2Link>
                    <Row2Link $light={themeStore.light} href="#">Informações corporativas</Row2Link>
                </Row2>
                
                <Row3 $light={themeStore.light}>
                    <ThemeSwitch />
                    <LanguageSelect />
                </Row3>
            </Footer>
        </Layout>
    );
}

const Layout = styled.div<{$isMedium: boolean, $light: boolean}>`
    min-height: 100vh; 
    width: 100%;

    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;

    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    background-color: ${props => props.$isMedium ? (props.$light? styles.neutral_100 : styles.neutral_900) : (props.$light? styles.neutral_100 : styles.neutral_850)};
`;

const Header = styled.header<{}>`
    display: flex;
    justify-content: flex-start;
    align-items: center;

    width: 100%;
    height: 96px;
    padding-left: 24px;
`;

const Logo = styled.img<{}>`
    height: 48px;
    width: auto;
`;

const Footer = styled.footer<{$light: boolean, $isMedium: boolean}>`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    gap: 16px;

    width: 100%;
    padding: 24px 32px;
    margin-top: 76px;
    background-color: ${props => props.$light? styles.neutral_300 : styles.neutral_900};
    border-top: ${props => props.$isMedium? ("1px solid "+styles.neutral_300) : "none"};
`;

const Row1 = styled.div<{$light: boolean}>`
    margin-bottom: 12px;
    font-size: ${styles.font_size_normal};
    color: ${styles.neutral_100}
`;

const NumberLink = styled.a<{$light: boolean}>`
    color: ${styles.neutral_400};
    text-decoration: none;
    cursor: pointer;

    &:visited{
        color: ${styles.neutral_400};
        text-decoration: none;
    }
    &:hover{
        text-decoration: underline;
    }
`;

const Row2 = styled.div<{$light: boolean}>`
    width: 100%;
    display: grid;
    grid-template-columns: repeat( auto-fit, minmax(100px, 1fr) );
    column-gap: 36px;
    row-gap: 14px;
`;

const Row2Link = styled.a<{$light: boolean}>`
    color: ${styles.neutral_400};
    font-size: ${styles.font_size_small};
    text-decoration: none;

    &:visited{
        color: ${styles.neutral_400};
        text-decoration: none;
    }
    &:hover{
        text-decoration: underline;
    }
`;

const Row3 = styled.div<{$light: boolean}>`
`;