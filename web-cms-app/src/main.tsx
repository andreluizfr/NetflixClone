import Router from "@Main/routes/Router";

import { ReduxProvider } from '@Infrastructure/store/redux/provider';
import { ReactQueryProvider } from '@Main/providers/ReactQueryProvider';

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<ReduxProvider>
		<ReactQueryProvider>
			<React.StrictMode>
				<Router />
			</React.StrictMode>
		</ReactQueryProvider>
	</ReduxProvider>,
);
