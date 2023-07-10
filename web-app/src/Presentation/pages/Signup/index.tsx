import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';
import checkmark from '../../assets/img/checkmark.png';
import checkmarkGroup from '../../assets/svg/checkmark-group.svg';

import { Link, useNavigate } from "react-router-dom";
import { useEffect } from 'react';

import { motion } from 'framer-motion';
import { Helmet } from 'react-helmet-async';

import { StoreState } from '@Infrastructure/stores/redux/config';
import { saveStep } from '@Infrastructure/stores/redux/features/signupDataSlice';
import { useDispatch, useSelector } from 'react-redux';

export default function SignupPage(): JSX.Element {

	const width = window.innerWidth;

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);
    const signup = useSelector((state: StoreState) => state.signup);

    useEffect(()=>{
        if(user.data?.account.isActive){
            navigate("/contents");
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    function goToNextStep(){
        dispatch(saveStep(2));
    }

    function goToPlanForm() {
        dispatch(saveStep(1));
        navigate("/signup/planform");
    }

    return (
        <motion.div 
            className='SignupPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >
            <Helmet>
                <meta property="og:title" content="Netflix Signup" />
                <meta property="og:url" content="http://localhost:5173/signup" />
                <meta property="og:image" content={logo} />
                <meta property="og:image:alt" content="Netflix logo" />
                <meta property="og:description" content="Signup for the best streaming site for watching Movies, Tv Series and Animes" />
                <meta property="og:site_name" content="Netflix" />
            </Helmet>

            <header className='Header'>
                <Link to="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </Link>

                <Link to="/login" className='Login-link'>
                    Entrar
                </Link>
            </header>

            <main className='Steps-container'>
                {signup.step===1 &&
                    <div className='Signup-container-1'>

                        <img 
                            className='Icon' 
                            src={devices}
                            alt='devices icon'
                        />

                        <span className='Step-info'>PASSO 1 DE 3</span>

                        <h1 className='Title'>Termine de configurar sua conta</h1>

                        <p className='Description'>
                            A Netflix é personalizada para você. Crie uma senha para começar a assistir à Netflix.
                        </p>

                        <button className='NextStep-button' onClick={goToNextStep}>
                            Próximo
                        </button>

                    </div>
                }

                {signup.step===2 &&
                    <div className='Signup-container-2'>

                        <img 
                            className='Icon' 
                            src={checkmark}
                            alt='checkmark icon'
                        />

                        <span className='Step-info'>PASSO 1 DE 3</span>

                        <h1 className='Title'>Escolha seu plano.</h1>

                        <div className='Advantages'>
                            <img 
                                className='Icon'
                                src={checkmarkGroup}
                                alt='checkmark icon'
                            />
                            <p className='Text'>Sem compromisso, cancele quando quiser.</p>
                        </div>
                        <div className='Advantages'>
                            <img 
                                className='Icon'
                                src={checkmarkGroup}
                                alt='checkmark icon'
                            />
                            <p className='Text'>Entretenimento sem fim, por um preço baixo.</p>
                        </div>
                        <div className='Advantages'>
                            <img 
                                className='Icon'
                                src={checkmarkGroup}
                                alt='checkmark icon'
                            />
                            <p className='Text'>Divirta-se com a Netflix em todos os seus aparelhos.</p>
                        </div>

                        <button className='NextStep-button' onClick={goToPlanForm}>
                            <a href="#">
                                Próximo
                            </a>
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