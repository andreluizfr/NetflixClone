import React from 'react';
import ReactDOM from 'react-dom/client';
import Router from './Router.tsx';
import './index.css';

import { QueryClientProvider } from 'react-query';
import { queryClient } from './libs/react-query';

import { Provider } from 'react-redux';
import store from './store';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <Provider store={store}>
		<QueryClientProvider client={queryClient}>
      <React.StrictMode>
        <Router />
      </React.StrictMode>
    </QueryClientProvider>
  </Provider>,
);
