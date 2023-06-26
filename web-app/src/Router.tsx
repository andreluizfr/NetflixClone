import { lazy, Suspense } from 'react';

import {
  createBrowserRouter,
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
            path: "/contents",
            element: <ContentsPage/>,
          },
          
        ])
      }/> 
    </Suspense>
  );
}

export default App
