import { lazy, Suspense, useEffect } from 'react';

import {
	createBrowserRouter,
	RouteObject,
	RouterProvider
} from "react-router-dom";

import LoadingPage from "./pages/Loading";

const HomePage = lazy(() => import('./pages/Home'));
const LoginPage = lazy(() => import('./pages/Login'));
const SignupPage = lazy(() => import('./pages/Signup'));
const SignupPlanPage = lazy(() => import('./pages/SignupPlan'));
const SignupRegistrationPage = lazy(() => import('./pages/SignupRegistration'));
const SignupPaymentPickerPage = lazy(() => import('./pages/SignupPaymentPicker'));
const SignupInformationsPage = lazy(() => import('./pages/SignupInformations'));
const SignupPaymentPage = lazy(() => import('./pages/SignupPayment'));
const ContentsPage = lazy(() => import('./pages/Contents'));

import { useSelector } from 'react-redux';
import { StoreState } from './store';
import FetchUser from './queries/Users/FetchUser';

function App() {

	const user = useSelector((state: StoreState) => state.user);
	
	const fetchUserQuery = FetchUser();
	
  	useEffect(()=>{
		if(fetchUserQuery.data)
      		console.log(fetchUserQuery.data);
	}, [fetchUserQuery.data]);

 	if(fetchUserQuery.isFetching || fetchUserQuery.isLoading)
		return(
			<RouterProvider router={
				createBrowserRouter([
					{
						path: "*",
						element: <LoadingPage/>, 
					}
				])
			}/>
		);

	else if(fetchUserQuery.isError)
		return (
			<RouterProvider router={
				createBrowserRouter([
					{
						path: "*",
						element: <>{(fetchUserQuery.error as Error).message}</>,
					}
				])
			}/>
		);
			
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
				{
					path: "/signup",
					element: <SignupPage/>,
				},
				{
					path: "/signup/planform",
					element: <SignupPlanPage/>,
				},
				{
					path: "/signup/registration",
					element: <SignupRegistrationPage/>,
				},
				{
					path: "/signup/paymentPicker",
					element: <SignupPaymentPickerPage/>,
				},
				{
					path: "/signup/informations",
					element: <SignupInformationsPage/>,
				},
				{
					path: "/signup/payment",
					element: <SignupPaymentPage/>,
				},
				user &&
				{
					path: "/contents",
					element: <ContentsPage/>,
				},
				{
					path: "*",
					element: <>Página não encontrada</>,
				}
			].filter(Boolean) as RouteObject[])
		}/> 
    </Suspense>
  );
}

export default App
