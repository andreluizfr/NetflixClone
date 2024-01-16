import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';
import AnimatedInput from '../../components/AnimatedInput';

import { StoreState } from '@Infrastructure/store/redux/config';
import { saveEmail, savePassword, saveStep } from '@Infrastructure/store/redux/features/signupDataSlice';
import { useDispatch, useSelector } from 'react-redux';

import { Link, useNavigate } from "react-router-dom";
import { useEffect } from 'react';

import { motion } from 'framer-motion';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function SignupRegistrationPage(): JSX.Element {

    //  ############# Redirecionamento de página ##################
    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);
    const signup = useSelector((state: StoreState) => state.signup);

    useEffect(()=>{

        if(user.data?.account?.active){
            navigate("/browse");
        }
        //próxima página, se tem usuário logado com plano ou dados de email, senha com plano também
        else if(user.data && signup.plan!==null){ 
            navigate("/signup/paymentPicker");
        }
        else if(signup.plan===null){ //página anterior, se nao tem os dados de plan
            toast.error("Alguns dos seus dados estão faltando, você terá que reiniciar o processo.", {
                position: "top-center",
                hideProgressBar: false
            });
            setTimeout(()=>navigate("/signup"), 2000);
        }
    }, []);


    //  ############# Manipulação de dados na view ##################
    const dispatch = useDispatch();

    function goToNextStep(){
        dispatch(saveStep(2));
    }

    function goToPaymentPicker(){

        const inputs = document.getElementsByClassName("Input") as HTMLCollectionOf<HTMLInputElement>;
        
        if(!signup.email) {
            dispatch(saveEmail(inputs[0].value));
            dispatch(savePassword(inputs[1].value));
        } else 
            dispatch(savePassword(inputs[0].value));
        
        dispatch(saveStep(1));
        navigate("/signup/paymentPicker");
    }


    //  ############# Renderização do conteúdo ##################
    const width = window.innerWidth;

    return (
        <motion.div 
            className='SignupRegistrationPage'
            initial={{ x: -(width/2), opacity: 0}}
			animate={{x: 0, opacity: 1, transition:{type: "easeIn", duration: 0.6}}}
        >
             <ToastContainer
                autoClose={2000} //mesmo tempo do redirecionamento feito pelo serviço do login
                newestOnTop={true}
                pauseOnFocusLoss={false}
                draggable={false}
                pauseOnHover={false}
                hideProgressBar={false}
                theme="light"
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
                {signup.step===1 &&
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

                {signup.step===2 &&
                    <div className='Signup-container-2'>

                        <span className='Step-info'>PASSO 2 DE 3</span>

                        <h1 className='Title'>Crie uma senha para iniciar sua assinatura.</h1>

                        <p className='Messages'>
                            Faltam só mais alguns passos!
                        </p>
                        <p className='Messages'>
                            Nós também detestamos formulários.
                        </p>

                        {!signup.email &&
                            <AnimatedInput 
                                title="Email" 
                                warning="O email é obrigatório."
                                theme="light"
                                type="email"
                                minLength={3}
                                maxLength={128}
                                required
                            />
                        }

                        <AnimatedInput 
                            title="Adicione uma senha" 
                            warning="A senha é obrigatória."
                            theme="light"
                            type="password"
                            minLength={8}
                            maxLength={32}
                            required
                        />

                        <button className='NextStep-button' onClick={goToPaymentPicker}>
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