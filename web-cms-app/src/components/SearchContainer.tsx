import React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import TransferList from './TransferList';

export default function SearchContainer() {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>

        <Button variant="outlined" onClick={handleClickOpen} color='primary'>
            Empregos
        </Button>

        <Button variant="outlined" onClick={handleClickOpen} color='primary'>
            Oportunidades
        </Button>

        <Dialog
            onClose={handleClose}
            aria-labelledby="customized-dialog-title"
            open={open}
        >

            <DialogTitle sx={{ m: 0, p: 2 }} id="customized-dialog-title">
                Selecionar as empresas
            </DialogTitle>
        
            <IconButton
                aria-label="close"
                onClick={handleClose}
                sx={{
                    position: 'absolute',
                    right: 8,
                    top: 8,
                    color: "lightgray",
                }}
            >
                <CloseIcon />
            </IconButton>

            <DialogContent dividers>
                <Typography gutterBottom>
                    Cras mattis consectetur purus sit amet fermentum. Cras justo odio,
                    dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac
                    consectetur ac, vestibulum at eros.
                </Typography>

                <TransferList/>
            </DialogContent>

            <DialogActions>
                <Button autoFocus onClick={handleClose}>
                    Save changes
                </Button>
            </DialogActions>

        </Dialog>

    </Box>
  );
}