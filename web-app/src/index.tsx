import Router from "@Main/routes/Router";
import { HelmetProvider } from '@Main/providers/HelmetProvider.tsx';

import { ReduxProvider } from '@Infrastructure/stores/redux/provider';
import { ReactQueryProvider } from '@Main/providers/ReactQueryProvider';

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<ReduxProvider>
		<ReactQueryProvider>
			<HelmetProvider>
				<React.StrictMode>
					<Router />
				</React.StrictMode>
			</HelmetProvider>
		</ReactQueryProvider>
	</ReduxProvider>,
);
