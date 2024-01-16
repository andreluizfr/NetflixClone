import { selectLanguage } from '@Infrastructure/store/redux/features/languageSlice';
import { StoreState } from '@Infrastructure/store/redux/config';

import styles from "@Model/constants/styles.json";

import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import styledComponent from 'styled-components';
import { styled } from '@mui/material/styles';

import { useDispatch, useSelector } from 'react-redux';
import Divider from '@mui/material/Divider';

export default function LanguageSelect() {
  const dispatch = useDispatch();
  const themeStore = useSelector((state: StoreState) => state.theme);
  const languageStore = useSelector((state: StoreState) => state.language);

  const handleChange = (event: SelectChangeEvent<unknown>) => {
    dispatch(selectLanguage(String(event.target.value)));
  };

  const StyledSelect = styled(Select)(() => ({
    '& .MuiSelect-select': {
      padding: "4px 6px 4px 16px",
      backgroundColor: themeStore.light ? styles.neutral_100 : styles.neutral_900
    },
    '& .MuiOutlinedInput-notchedOutline': {
      borderColor: themeStore.light ? styles.neutral_400 : styles.neutral_100,
      borderWidth: "1px"
    },
    '&:hover': {
      '& .MuiOutlinedInput-notchedOutline': {
        borderColor: themeStore.light ? styles.red_primary : styles.blue_600 ,
        borderWidth: themeStore.light ? "1px" : "2px"
      }
    },
    '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
      borderColor: themeStore.light ? styles.red_primary : styles.blue_600,
      borderWidth: themeStore.light ? "1px" : "2px"
    },
    '& .MuiPaper-root':{
      backgroundColor: themeStore.light ? styles.neutral_100 : styles.neutral_900,
    },
    '& .MuiSvgIcon-root': {
      color: themeStore.light ? styles.neutral_450 : styles.neutral_100
    }
  }));

  const StyledMenuItem = styled(MenuItem)(() => ({
    backgroundColor: themeStore.light ? styles.neutral_100 : styles.neutral_900,
    '&:hover': {
      backgroundColor: themeStore.light ? "rgba(210, 25, 34, 0.24)" : styles.blue_primary
    },
    '&.Mui-selected': {
      backgroundColor: themeStore.light ? styles.neutral_100 : styles.neutral_900,
      '& span': {
        color: themeStore.light ? styles.neutral_900+" !important" : styles.neutral_100+" !important"
      },
      '&:hover': {
        backgroundColor: themeStore.light ? "rgba(210, 25, 34, 0.24)" : styles.blue_primary
      },
      '+.MuiDivider-root': {
        margin: 0,
        borderColor: "rgba(64, 64, 64, 0.24)"
      }
    },

  }));

  return (
    <FormControl sx={{ m: 1 }} >
      <StyledSelect
        value={languageStore.selectedLanguage}
        onChange={handleChange}
        displayEmpty
        inputProps={{
          'aria-label': 'Without label',
            MenuProps: {
              PaperProps: {
                sx: {
                   backgroundColor: themeStore.light ? styles.neutral_100 : styles.neutral_900,
                   border: themeStore.light ? "none" : "1px solid "+styles.neutral_600,
                   boxShadow:  themeStore.light ? "0px 5px 5px -3px rgba(0,0,0,0.2), 0px 8px 10px 1px rgba(0,0,0,0.14), 0px 3px 14px 2px rgba(0,0,0,0.12)" 
                    : "0px 5px 5px -3px rgba(22,22,22,0.2), 0px 8px 10px 1px rgba(22,22,22,0.14), 0px 3px 14px 2px rgba(22,22,22,0.12)"
                }
              }
            }
          }}
        variant='outlined'
      >
        <StyledMenuItem value={"pt-BR"}>
          <SelectText $light={themeStore.light}>
            {languageStore.labels.portuguese}
          </SelectText>
        </StyledMenuItem>
        
        <Divider />
        
        <StyledMenuItem value={"en-US"}>
          <SelectText $light={themeStore.light}>
            {languageStore.labels.english}
          </SelectText>
        </StyledMenuItem>

      </StyledSelect>
    </FormControl>
  );
}

const SelectText =  styledComponent.span<{$light: boolean}>`
  color: ${props => props.$light? styles.neutral_500 : styles.neutral_200};
  font-size: 12px;
  font-weight: 600;
  font-family: Netflix Sans;
`;
