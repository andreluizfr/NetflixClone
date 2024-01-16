import Router from "@Main/routes/Router";

import { ReduxProvider } from '@Infrastructure/store/redux/provider';
import { ReactQueryProvider } from '@Main/providers/ReactQueryProvider';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<ReduxProvider>
		<ReactQueryProvider>
			<React.StrictMode>
				<Router />
				<ToastContainer
					autoClose={2000} //mesmo tempo do redirecionamento feito pelo serviço do login
					newestOnTop={true}
					pauseOnFocusLoss={true}
					draggable={true}
					pauseOnHover={true}
					hideProgressBar={false}
					rtl={false}
					theme="dark"
					position="top-center"
				/>
			</React.StrictMode>
		</ReactQueryProvider>
	</ReduxProvider>,
);
