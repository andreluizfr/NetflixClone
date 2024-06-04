import * as React from 'react';
import Grid from '@mui/material/Grid';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';

interface Company{
	name: string;
	link: string;
}

function not(a: readonly Company[], b: readonly Company[]) {
	return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a: readonly Company[], b: readonly Company[]) {
	return a.filter((value) => b.indexOf(value) !== -1);
}

export default function TransferList() {
	let allJobs: {jobs: {companies_added: readonly Company[], companies_to_add: readonly Company[]}} = {
		jobs: {
			companies_added: [{name: "haha", link: "haha"}],
			companies_to_add: [{name: "hoho", link: "hoho"}]
		}
	};

	const [checked, setChecked] = React.useState<readonly Company[]>([]);
	const [left, setLeft] = React.useState<readonly Company[]>(allJobs.jobs.companies_added);
	const [right, setRight] = React.useState<readonly Company[]>(allJobs.jobs.companies_to_add);

	const leftChecked = intersection(checked, left);
	const rightChecked = intersection(checked, right);

	const handleToggle = (value: Company) => () => {
		const currentIndex = checked.indexOf(value);
		const newChecked = [...checked];

		if (currentIndex === -1) {
			newChecked.push(value);
		} else {
			newChecked.splice(currentIndex, 1);
		}

		setChecked(newChecked);
	};

	const handleAllRight = () => {
		setRight([...right, ...left]);
		setLeft([]);
	};

	const handleCheckedRight = () => {
		setRight(right.concat(leftChecked));
		setLeft(not(left, leftChecked));
		setChecked(not(checked, leftChecked));
	};

	const handleCheckedLeft = () => {
		setLeft(left.concat(rightChecked));
		setRight(not(right, rightChecked));
		setChecked(not(checked, rightChecked));
	};

	const handleAllLeft = () => {
		setLeft([...left, ...right]);
		setRight([]);
	};

	const customList = (items: readonly Company[]) => (
		<Paper sx={{ width: 200, height: 230, overflow: 'auto' }}>
			<List dense component="div" role="list">
				{items.map((value: Company, index: number) => {
					const labelId = `transfer-list-item-${index}-label`;

					return (
						<ListItem
							key={index}
							role="listitem"
							button
							onClick={handleToggle(value)}
						>
							<ListItemIcon>
								<Checkbox
									checked={checked.indexOf(value) !== -1}
									tabIndex={-1}
									disableRipple
									inputProps={{
										'aria-labelledby': labelId,
									}}
								/>
							</ListItemIcon>
							<ListItemText id={labelId} primary={value.name} />
						</ListItem>
					);
				})}
			</List>
		</Paper>
	);

	return (
		<Grid container spacing={2} justifyContent="center" alignItems="center">
			<Grid item>{customList(left)}</Grid>
			<Grid item>
				<Grid container direction="column" alignItems="center">
					<Button
						sx={{ my: 0.5 }}
						variant="outlined"
						size="small"
						onClick={handleAllRight}
						disabled={left.length === 0}
						aria-label="move all right"
					>
						≫
					</Button>
					<Button
						sx={{ my: 0.5 }}
						variant="outlined"
						size="small"
						onClick={handleCheckedRight}
						disabled={leftChecked.length === 0}
						aria-label="move selected right"
					>
						&gt;
					</Button>
					<Button
						sx={{ my: 0.5 }}
						variant="outlined"
						size="small"
						onClick={handleCheckedLeft}
						disabled={rightChecked.length === 0}
						aria-label="move selected left"
					>
						&lt;
					</Button>
					<Button
						sx={{ my: 0.5 }}
						variant="outlined"
						size="small"
						onClick={handleAllLeft}
						disabled={right.length === 0}
						aria-label="move all left"
					>
						≪
					</Button>
				</Grid>
			</Grid>
			<Grid item>{customList(right)}</Grid>
		</Grid>
	);
}