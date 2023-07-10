import { lazy, Suspense } from 'react';

import LoadingPage from "@Presentation/pages/Loading";
const HomePage = lazy(() => import('@Presentation/pages/Home'));
const LoginPage = lazy(() => import('@Presentation/pages/Login'));
const SignupPage = lazy(() => import('@Presentation/pages/Signup'));
const SignupPlanPage = lazy(() => import('@Presentation/pages/SignupPlan'));
const SignupRegistrationPage = lazy(() => import('@Presentation/pages/SignupRegistration'));
const SignupPaymentPickerPage = lazy(() => import('@Presentation/pages/SignupPaymentPicker'));
const SignupInformationsPage = lazy(() => import('@Presentation/pages/SignupInformations'));
const SignupPaymentPage = lazy(() => import('@Presentation/pages/SignupPayment'));
const ContentsPage = lazy(() => import('@Presentation/pages/Contents'));

import { FetchUserImpl } from '@Application/useCases/FetchUser/FetchUserImpl';

import { StoreState } from '@Infrastructure/stores/redux/config';
import { useSelector } from 'react-redux';


import {
	createBrowserRouter,
	RouteObject,
	RouterProvider
} from "react-router-dom";

function Router() {

	const user = useSelector((state: StoreState) => state.user);
	
	FetchUserImpl();
	
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
					//user.data && localStorage.getItem("x-access-token") &&
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

export default Router;