import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';

import { Link, useNavigate } from "react-router-dom";

import { useDispatch, useSelector } from 'react-redux';
import { StoreState } from '../../store';
import { saveBirthdayDate } from '../../store/features/signupSlice';
import { useEffect } from 'react';

import { useForm } from "react-hook-form";

import { motion } from 'framer-motion';

interface registerForm{
    birthdayDate: Date,
    checkbox: boolean
}

export default function SignupInformationsPage(): JSX.Element{

    const width = window.innerWidth;

    const navigate = useNavigate();

    const signup = useSelector((state: StoreState) => state.signup);
    const dispatch = useDispatch();

    const { register, handleSubmit } = useForm<registerForm>();

    function onSubmit (data: registerForm){
        dispatch(saveBirthdayDate(data.birthdayDate));
        //dar refetch na request do react query
        navigate("/contents");
    };
    
    useEffect(()=>{
        if(!(signup.email!==null && signup.password!==null && signup.plan!==null && signup.payment!==null)){
            navigate("/signup");
        }
    }, []);

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
                            {...register("birthdayDate")}
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