import { lazy, Suspense, useState } from 'react';

import LoadingPage from "@Presentation/pages/Loading";
const HomePage = lazy(() => import('@Presentation/pages/Home'));
const LoginPage = lazy(() => import('@Presentation/pages/Login'));
const SignupPage = lazy(() => import('@Presentation/pages/Signup'));
const SignupPlanPage = lazy(() => import('@Presentation/pages/SignupPlan'));
const SignupRegistrationPage = lazy(() => import('@Presentation/pages/SignupRegistration'));
const SignupPaymentPickerPage = lazy(() => import('@Presentation/pages/SignupPaymentPicker'));
const SignupInformationsPage = lazy(() => import('@Presentation/pages/SignupInformations'));
const SignupPaymentPage = lazy(() => import('@Presentation/pages/SignupPayment'));
const WhoIsWatchingPage = lazy(() => import('@Presentation/pages/WhoIsWatching'));
const BrowsePage = lazy(() => import('@Presentation/pages/Browse'));
const TrackPage = lazy(() => import('@Presentation/pages/Track'));

import {
	createBrowserRouter,
	RouterProvider
} from "react-router-dom";

import { AuthProvider } from '@Main/providers/AuthProvider';

function Router() {

	const [fontsLoaded, setFontsLoaded] = useState(false);

	window.onload = function() {
		if (document.fonts.check('16px Netflix Sans')) {
			setFontsLoaded(true);
		} else {
			setTimeout(function() {
				setFontsLoaded(true);
			}, 2000);
		}
	}
	
	if (fontsLoaded)
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
						{
							path: "/whoIsWatching",
							element: <AuthProvider><WhoIsWatchingPage/></AuthProvider>,
						},
						{
							path: "/browse",
							element: <AuthProvider><BrowsePage/></AuthProvider>,
						},
						{
							path: "/watch",
							//element: <AuthProvider><TrackPage/></AuthProvider>,
							element: <TrackPage/>,
						},
						{
							path: "/403",
							element: <>Você não tem permissão pra acessar essa página</>,
						},
						{
							path: "*",
							element: <>Página não encontrada</>,
						}
					])
				}/> 
			</Suspense>
		);

	else return <LoadingPage/>
}

export default Router;