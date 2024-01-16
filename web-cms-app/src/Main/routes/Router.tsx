import { lazy, Suspense } from 'react';

import Layout from '@Presentation/components/Layout';
import LoadingPage from "@Presentation/pages/LoadingPage";
const LoginPage = lazy(() => import('@Presentation/pages/LoginPage'));
const CmsPage = lazy(() => import('@Presentation/pages/CmsPage'));

import {
	createBrowserRouter,
	RouterProvider
} from "react-router-dom";

import { AuthProvider } from '@Main/providers/AuthProvider';


function Router() {

	return (
		<Suspense fallback={<LoadingPage/>}>
			<Layout>
				<RouterProvider router={
					createBrowserRouter([
						{
							path: "/login",
							element: <LoginPage/>,
						},
						{
							path: "/cms",
							element: <AuthProvider><CmsPage/></AuthProvider>,
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
			</Layout>
		</Suspense>
	);
}

export default Router;