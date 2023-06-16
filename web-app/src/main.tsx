import React from 'react';
import ReactDOM from 'react-dom/client';
import Router from './Router.tsx';
import './index.css';

import { QueryClientProvider } from 'react-query';
import { queryClient } from './libs/react-query';

import { Provider } from 'react-redux';
import store from './store';

import { HelmetProvider } from 'react-helmet-async';
const helmetContext = {};

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <Provider store={store}>
		<QueryClientProvider client={queryClient}>
      <HelmetProvider context={helmetContext}>
        <React.StrictMode>
          <Router />
        </React.StrictMode>
      </HelmetProvider>
    </QueryClientProvider>
  </Provider>,
);
