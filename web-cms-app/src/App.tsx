import React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import SearchContainer from './components/SearchContainer';
import Container from '@mui/material/Container';

interface TabPanelProps {
	children?: React.ReactNode;
	dir?: string;
	index: number;
	value: number;
}

function TabPanel(props: TabPanelProps) {
	const { children, value, index, ...other } = props;
  
	return (
		<div
			role="tabpanel"
			hidden={value !== index}
			id={`full-width-tabpanel-${index}`}
			aria-labelledby={`full-width-tab-${index}`}
			{...other}
		>
			{value === index && (
				<Container maxWidth="sm" sx={{ p: 3 }}>
					<Typography>{children}</Typography>
				</Container>
			)}
		</div>
	);
}

function App() {

	const [value, setValue] = React.useState(0);

	const handleChange = (event: React.SyntheticEvent, newValue: number) => {
		event.preventDefault();
		setValue(newValue);
	};

	return (
		<div className='px-[5px]'>
			<div className='flex gap-2'>

				<Box sx={{ bgcolor: '#242424', width: '90vw', height: '90vh' }}>

					<Tabs
						value={value}
						onChange={handleChange}
						textColor="secondary"
						indicatorColor="secondary" //tracinho embaixo dos itens
						variant="fullWidth"
						aria-label="full width tabs example"
						sx={{bgcolor: '#FFFFFF'}}
					>
						<Tab label="Meus Itens" id="full-width-tab-0" aria-controls="full-width-tabpanel-0" sx={{color: "#A9A9A9"}}/>
						<Tab label="Editar Empresas" id="full-width-tab-1" aria-controls="full-width-tabpanel-1" sx={{color: "#A9A9A9"}}/>
						<Tab label="Pesquisar Empresas" id="full-width-tab-2" aria-controls="full-width-tabpanel-2" sx={{color: "#A9A9A9"}}/>
					</Tabs>

					<TabPanel value={value} index={0}>
						<SearchContainer />
					</TabPanel>

					<TabPanel value={value} index={1}>
						<SearchContainer />
					</TabPanel>

					<TabPanel value={value} index={2}>
						<SearchContainer />
					</TabPanel>

				</Box>

			</div>
		</div>
	)
}

export default App
