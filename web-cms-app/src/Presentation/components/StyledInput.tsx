import styledComponent from 'styled-components';
import styles from "@Model/constants/styles.json";

import { InputHTMLAttributes, useState } from "react";
import TextField from '@mui/material/TextField';
import IconButton from '@mui/material/IconButton';
import { useSelector } from 'react-redux';
import { StoreState } from '@Infrastructure/store/redux/config';
import { styled } from '@mui/material/styles';

interface Props extends InputHTMLAttributes<HTMLInputElement> {
    title?: string;
    warning?: string | undefined;
    hasShow?: boolean | undefined;
    inputValue: string | null;
    setInputValue: React.Dispatch<React.SetStateAction<string | null>>;
}

export default function StyledInput(props:Props): JSX.Element {

    const {title, warning, hasShow, required, minLength, maxLength, inputValue, setInputValue} = props;

    const themeStore = useSelector((state: StoreState) => state.theme);

    const [show, setShow] = useState(true);
    const [error, setError] = useState(false);

    function validate(event: React.FocusEvent<HTMLInputElement>) {
        if(required){
            if(event.target.value===null || event.target.value===undefined || event.target.value==="")
                setError(true);
            else if(minLength!==undefined && event.target.value.length<minLength)
                setError(true);
            else if(maxLength!==undefined && event.target.value.length>maxLength)
                setError(true);
            else 
                setError(false);
        }
        setInputValue(event.target.value);
    }

    function toggleShow(){
        if(show===true) setShow(false);
        else setShow(true);
    }

    const StyledTextField = styled(TextField)(() => ({
        width: "100%",
        '& .MuiInputLabel-root': {
            color: themeStore.light? styles.neutral_400+" !important" : styles.neutral_300+" !important"
        },
        '& .MuiOutlinedInput-input': {
            color: themeStore.light? styles.neutral_400 : styles.neutral_300,
        },
        '& .MuiOutlinedInput-notchedOutline': {
            borderColor: themeStore.light? styles.neutral_400 : styles.neutral_300
        },
        
        '& .Mui-focused .MuiOutlinedInput-notchedOutline': {
            borderColor: themeStore.light? styles.neutral_400+" !important" : styles.neutral_300+" !important"
        },
        '& .MuiOutlinedInput-root:hover .MuiOutlinedInput-notchedOutline': {
            borderColor: themeStore.light? styles.neutral_400 : styles.neutral_300
        },
        '& .Mui-error .MuiOutlinedInput-notchedOutline': {
            borderColor: styles.red_primary+" !important",
        }
    }));

    return (
        <Input>
            <StyledTextField
                label={title}
                type={show?"text":"password"}
                InputProps={hasShow?{
                    endAdornment:<IconButton onClick={toggleShow} sx={{fontSize: "14px", color: themeStore.light?styles.neutral_400:styles.blue_primary}}>
                                    MOSTRAR
                                </IconButton>,
                }:{}}
                defaultValue={inputValue}
                onBlur={validate}
                error={error}
            />
            <WarningMessage $error={error}>{warning}</WarningMessage>
        </Input>
    )

}

const Input = styledComponent.div<{}>`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    position: relative;
    margin-bottom: 4px;
    width: 100%;
`;

const WarningMessage = styledComponent.p<{$error: boolean}>`
    font-size: ${styles.font_size_small};
    color: ${styles.red_primary};
    visibility: ${props => props.$error ? "visible" : "hidden"};
`;


