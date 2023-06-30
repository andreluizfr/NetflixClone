import './styles.css';
import logo from '../../assets/svg/logo.svg';

import { Link, useNavigate } from "react-router-dom";

import { useSelector } from 'react-redux';
import { StoreState } from '../../store';

import { motion } from 'framer-motion';

import { useEffect } from 'react';

import { initMercadoPago, Wallet} from '@mercadopago/sdk-react';
import CreatePlanPaymentQuery from '../../queries/Payments/CreatePlanPayment';

export default function SignupPaymentPage(): JSX.Element{

    initMercadoPago(import.meta.env.VITE_MERCADO_PAGO_PUBLIC_KEY);

    const width = window.innerWidth;

    const navigate = useNavigate();
    const signup = useSelector((state: StoreState) => state.signup);
    const user = useSelector((state: StoreState) => state.user);

    const createPlanPaymentQuery = CreatePlanPaymentQuery(user.data?.account.id, signup.plan, signup.paymentType);

    useEffect(()=>{
        if(!user.data || signup.paymentType === null || signup.plan === null){
            navigate("/signup");
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    useEffect(()=>{
        if(user.data?.account.id!==null && signup.plan!==null && signup.paymentType!==null)
            createPlanPaymentQuery.refetch();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [user.data?.account.id, signup.plan, signup.paymentType]);
        
    return (
        <motion.div 
            className='SignupPaymentPickerPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >
            <header className='Header'>
                <Link to="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </Link>

                <Link to="/login" className='Login-link'>
                    Entrar
                </Link>
            </header>

            <main className='Payment-container'>
                {createPlanPaymentQuery.isLoading &&
                    <div>Loading...</div>
                }
                {createPlanPaymentQuery.data?.data &&
                    <Wallet initialization={{ preferenceId: createPlanPaymentQuery.data.data.id }} />
                }
                {user && //solução temporária
                    <Link to="/login">Pular pra login sem realizar pagamento</Link>
                }
            </main>

            <footer>
                <div className='Row-1'>
                    Dúvidas? Ligue <a href="#">0800 591 8942</a>
                </div>

                <div className='Row-2'>
                    <a href="#">Perguntas frequentes</a>
                    <a href="#">Central de Ajuda</a>
                    <a href="#">Netflix Shop</a>
                    <a href="#">Termos de Uso</a>
                    <a href="#">Privacidade</a>
                    <a href="#">Preferências de cookies</a>
                    <a href="#">Informações corporativas</a>
                </div>
                
                <div className='Row-3'>
                    <select className="Select-language" defaultValue={"pt"}>
                        <option value="pt">Português</option>
                        <option value="en">English</option>
                    </select>
                </div>
            </footer>

        </motion.div>
    );

}