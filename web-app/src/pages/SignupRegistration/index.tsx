import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';
import AnimatedInput from '../../components/AnimatedInput';

import { Link } from "react-router-dom";
import { useState } from 'react';

import { useDispatch } from 'react-redux';
import { saveEmail, savePassword} from '../../store/features/signupSlice';

import { motion } from 'framer-motion';

export default function SignupRegistrationPage(): JSX.Element {

	const width = window.innerWidth;

    const dispatch = useDispatch();

    const [step, setStep] = useState(1);

    function goToNextStep(){
        setStep(current=>current+1);
    }

    function saveData(){
        const inputs = document.getElementsByClassName("Input") as HTMLCollectionOf<HTMLInputElement>;
        dispatch(saveEmail(inputs[0].value));
        dispatch(savePassword(inputs[1].value));
    }

    return (
        <motion.div 
            className='SignupRegistrationPage'
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
                {step===1 &&
                    <div className='Signup-container-1'>

                        <img 
                            className='Icon' 
                            src={devices}
                            alt='devices icon'
                        />

                        <span className='Step-info'>PASSO 2 DE 3</span>

                        <h1 className='Title'>Termine de configurar sua conta</h1>

                        <p className='Description'>
                            A Netflix é personalizada para você. Crie uma senha para começar a assistir à Netflix.
                        </p>

                        <button className='NextStep-button' onClick={goToNextStep}>
                            Próximo
                        </button>

                    </div>
                }

                {step===2 &&
                    <div className='Signup-container-2'>

                        <span className='Step-info'>PASSO 2 DE 3</span>

                        <h1 className='Title'>Crie uma senha para iniciar sua assinatura.</h1>

                        <p className='Messages'>
                            Faltam só mais alguns passos!
                        </p>
                        <p className='Messages'>
                            Nós também detestamos formulários.
                        </p>

                        <AnimatedInput 
                            title="Email" 
                            warning="O email é obrigatório."
                            theme="light"
                            type="email"
                            minLength={3}
                            maxLength={128}
                            required
                        />

                        <AnimatedInput 
                            title="Adicione uma senha" 
                            warning="A senha é obrigatória."
                            theme="light"
                            type="password"
                            minLength={8}
                            maxLength={32}
                            required
                        />

                        <button className='NextStep-button' onClick={saveData}>
                            <Link to="/signup/paymentPicker">
                                Próximo
                            </Link>
                        </button>

                    </div>
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