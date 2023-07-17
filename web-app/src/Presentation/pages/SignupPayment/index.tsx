import './styles.css';
import logo from '@Presentation/assets/svg/logo.svg';

import { CreatePlanPaymentService } from '@Application/useCases/CreatePlanPayment/CreatePlanPaymentService';

import { StoreState } from '@Infrastructure/stores/redux/config';

import { Link, useNavigate } from "react-router-dom";

import { useSelector } from 'react-redux';

import { motion } from 'framer-motion';

import { useEffect } from 'react';

import { initMercadoPago, Wallet} from '@mercadopago/sdk-react';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function SignupPaymentPage(): JSX.Element{

    //  ############# Redirecionamento de página ##################
    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);
    
    useEffect(()=>{

        if(user.data?.account?.isActive){
            navigate("/contents");
        }
        else if(!user.data || signup.paymentType===null || signup.plan===null){
            toast.error("Alguns dos seus dados estão faltando, você terá que reiniciar o processo.", {
                position: "top-center",
                theme: "light"
            });
            setTimeout(()=>navigate("/signup"), 2000);
        }
    }, []);


    //  ############# Manipulação de requisição ##################
    const signup = useSelector((state: StoreState) => state.signup);

    const createPlanPaymentResult = CreatePlanPaymentService(user.data?.account?.id, signup.plan, signup.paymentType);

    useEffect(()=>{
        if(user.data?.account?.id!==null && signup.plan!==null && signup.paymentType!==null)
            createPlanPaymentResult.refetch();

    }, [user.data, signup.plan, signup.paymentType]);

    useEffect(()=>{
        if (createPlanPaymentResult.isError && createPlanPaymentResult.error)
            toast.error(createPlanPaymentResult.error.message, {
                position: "top-center",
                theme: "light"
            });
        else if (createPlanPaymentResult.data) {
            toast.success(createPlanPaymentResult.data.message, {
                position: "top-right",
                theme: "light",
            });
            toast.success(createPlanPaymentResult.data.message +
                "Clique no botão azul para realizar o pagamento com nossa operadora parceira.", {
                position: "top-right",
                theme: "light",
                autoClose: 5000
            });
        }

    }, [createPlanPaymentResult.isError, createPlanPaymentResult.error, createPlanPaymentResult.data]);


    //  ############# Renderização do conteúdo ##################
    initMercadoPago(import.meta.env.VITE_MERCADO_PAGO_PUBLIC_KEY);

    const width = window.innerWidth;
        
    return (
        <motion.div 
            className='SignupPaymentPickerPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >

            <ToastContainer
                autoClose={2000} //mesmo tempo do redirecionamento feito pelo serviço do login
                newestOnTop={true}
                pauseOnFocusLoss={false}
                draggable={false}
                pauseOnHover={false}
                hideProgressBar= {true}
            />
            
            <header className='Header'>
                <Link to="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </Link>

                <Link to="/login" className='Login-link'>
                    Entrar
                </Link>
            </header>

            <main className='Payment-container'>
                {createPlanPaymentResult.isLoading &&
                    <div>Loading...</div>
                }
                {createPlanPaymentResult.data?.data &&
                    <Wallet initialization={{ preferenceId: createPlanPaymentResult.data.data.id }} />
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