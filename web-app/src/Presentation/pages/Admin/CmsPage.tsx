import React from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import SearchContainer from '@/Presentation/components/SearchContainer';
import Container from '@mui/material/Container';
import useDashVideo from '@Presentation/hooks/useDashVideo';

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

function Cms() {

	const [value, setValue] = React.useState(0);

	const handleChange = (event: React.SyntheticEvent, newValue: number) => {
		event.preventDefault();
		setValue(newValue);
	};

	useDashVideo();

	return (
		<div className='px-[5px] w-full min-h-screen flex justify-center items-center'>
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

				<div className="dash-video-player ">
					<div className="videoContainer" id="videoContainer">

						<video preload="auto" autoPlay controls></video>
						
						<div id="videoController" className="video-controller unselectable">

							<div id="playPauseBtn" className="btn-play-pause" title="Play/Pause">
								<span id="iconPlayPause" className="icon-play"></span>
							</div>

							<span id="videoTime" className="time-display">00:00:00</span>

							<div id="fullscreenBtn" className="btn-fullscreen control-icon-layout" title="Fullscreen">
								<span className="icon-fullscreen-enter"></span>
							</div>

							<div id="bitrateListBtn" className="control-icon-layout" title="Bitrate List">
								<span className="icon-bitrate"></span>
							</div>

							<input type="range" id="volumebar" className="volumebar" value="1" min="0" max="1" step=".01"/>

							<div id="muteBtn" className="btn-mute control-icon-layout" title="Mute">
								<span id="iconMute" className="icon-mute-off"></span>
							</div>

							<div id="trackSwitchBtn" className="control-icon-layout" title="A/V Tracks">
								<span className="icon-tracks"></span>
							</div>

							<div id="captionBtn" className="btn-caption control-icon-layout" title="Closed Caption">
								<span className="icon-caption"></span>
							</div>

							<span id="videoDuration" className="duration-display">00:00:00</span>

							<div className="seekContainer">
								<div id="seekbar" className="seekbar seekbar-complete">
									<div id="seekbar-buffer" className="seekbar seekbar-buffer"></div>
									<div id="seekbar-play" className="seekbar seekbar-play"></div>
								</div>
							</div>

						</div>
					</div>
				</div>

			</div>
		</div>
	)
}

export default Cms;
