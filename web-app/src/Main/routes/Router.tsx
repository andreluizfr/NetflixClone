import { lazy, Suspense, useState } from 'react';

import LoadingPage from "@Presentation/pages/Client/Loading";
const HomePage = lazy(() => import('@Presentation/pages/Client/Home'));
const LoginPage = lazy(() => import('@Presentation/pages/Client/Login'));
const SignupPage = lazy(() => import('@Presentation/pages/Client/Signup'));
const SignupPlanPage = lazy(() => import('@Presentation/pages/Client/SignupPlan'));
const SignupRegistrationPage = lazy(() => import('@Presentation/pages/Client/SignupRegistration'));
const SignupPaymentPickerPage = lazy(() => import('@Presentation/pages/Client/SignupPaymentPicker'));
const SignupInformationsPage = lazy(() => import('@Presentation/pages/Client/SignupInformations'));
const SignupPaymentPage = lazy(() => import('@Presentation/pages/Client/SignupPayment'));
const WhoIsWatchingPage = lazy(() => import('@Presentation/pages/Client/WhoIsWatching'));
const BrowsePage = lazy(() => import('@Presentation/pages/Client/Browse'));
const TrackPage = lazy(() => import('@Presentation/pages/Client/Track'));

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
				<RouterProvider 
					router={
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
								element: <AuthProvider><TrackPage/></AuthProvider>,
								errorElement: <>Parece que estamos tendo alguns problemas nessa página</>
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
					}
					fallbackElement={<>Ocorreu um erro</>}
				/> 
			</Suspense>
		);

	else return <LoadingPage/>
}

export default Router;