import './styles.css';
import logo from '@Presentation/assets/svg/logo.svg';
import devices from '@Presentation/assets/img/devices.png';

import { CreateUserService } from '@Services/CreateUser/CreateUserService';
import { StoreState } from '@Infrastructure/store/redux/config';
import { useSelector } from 'react-redux';

import { Link, useNavigate } from "react-router-dom";

import { useEffect, useState } from 'react';

import { useForm } from "react-hook-form";

import { motion } from 'framer-motion';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import { Helmet } from 'react-helmet-async';

interface registerForm{
    birthDate: Date,
    checkbox: boolean
}

export default function SignupInformationsPage(): JSX.Element{

    //  ############# Redirecionamento de página ##################
    const navigate = useNavigate();
    const signup = useSelector((state: StoreState) => state.signup);
    const user = useSelector((state: StoreState) => state.user);

    useEffect(()=>{

        if(user.data?.account?.active){
            navigate("/browse");
        }
        else if(user.data!==null && signup.plan!==null && signup.paymentType!==null){ 
            navigate("/signup/payment");
        }
        else if(!signup.email || !signup.password || signup.plan===null || signup.paymentType===null){ 

            toast.error("Alguns dos seus dados estão faltando, você terá que reiniciar o processo.", {
                position: "top-center",
                theme: "light"
            });
            setTimeout(()=>navigate("/signup"), 2000);
        }
    }, []);


    //  ############# Manipulação de dados na view ##################
    const { register, handleSubmit } = useForm<registerForm>();
    const [birthDate, setBirthDate] = useState<Date | null>(null);
    const [readyToCreate, setReadyToCreate] = useState(false);
    
    const createUserResult = CreateUserService(signup.email, signup.password, birthDate);

    useEffect(()=>{
        
        if(birthDate && readyToCreate){

            createUserResult.refetch();

            setReadyToCreate(false);
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [birthDate, readyToCreate]);

    function onSubmit (data: registerForm){
        setBirthDate(data.birthDate);
        setReadyToCreate(true);
    }


    //  ############# Manipulação de requisição ##################
    useEffect(()=>{
        if(createUserResult.isError && createUserResult.error){
            toast.error(createUserResult.error.message, {
                position: "top-center",
                theme: "light"
            });
        }
    }, [createUserResult.data, createUserResult.error, createUserResult.isError]);


    //  ############# Renderização do conteúdo ##################
    const width = window.innerWidth;

    return (
        <motion.div 
            className='SignupPaymentPickerPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >
            
            <Helmet>
                <meta property="og:title" content="Netflix login page" />
                <meta property="og:url" content="http://localhost:5173/login" />
                <meta property="og:image" content={logo} />
                <meta property="og:image:alt" content="Netflix logo" />
                <meta property="og:description" content="Log in to watch the best Movies, Tv Series and Animes" />
                <meta property="og:site_name" content="Netflix" />
            </Helmet>

            <ToastContainer
                autoClose={2000} //mesmo tempo do redirecionamento feito pelo serviço do login
                newestOnTop={true}
                pauseOnFocusLoss={false}
                draggable={false}
                pauseOnHover={false}
                hideProgressBar={true}
            />
            
            <header className='Header'>
                <Link to="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </Link>

                <Link to="/login" className='Login-link'>
                    Entrar
                </Link>
            </header>

            <main className='Steps-container'>

                <form className='Register-form' onSubmit={handleSubmit(onSubmit)}>

                    <img 
                        className='Icon' 
                        src={devices}
                        alt='devices icon'
                    />

                    <span className='Step-info'>PASSO 3 DE 3</span>

                    <h1 className='Title'>Finalize seu cadastro</h1>

                    <div className='Input'>
                        <label htmlFor="checkbox">
                            Compartilhe conosco sua data de nascimento
                        </label>
                        <input 
                            type="date"
                            required
                            {...register("birthDate")}
                        />
                    </div>
                
                    <div className='Input Row'>
                        <input 
                            type="checkbox"
                            required
                            {...register("checkbox")}
                        />
                        <label htmlFor="checkbox">
                            Concordo com os termos do site.
                        </label>
                    </div>
                    
                    <button className='Register-button' type="submit">
                        Finalizar
                    </button>

                </form>

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