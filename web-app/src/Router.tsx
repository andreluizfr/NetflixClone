import { lazy, Suspense } from 'react';

import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom";

import LoadingPage from "./pages/Loading";
const HomePage = lazy(() => import('./pages/Home'));
const LoginPage = lazy(() => import('./pages/Login'));

function App() {

  return (
    <Suspense fallback={<LoadingPage/>}>
      <RouterProvider router={
        createBrowserRouter([
          {
            path: "/",
            element: <HomePage/>,
          },
          {
            path: "/login",
            element: <LoginPage/>,
          },
        ])
      }/> 
    </Suspense>
  );
}

export default App
