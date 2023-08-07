import './styles.css';
import logo from '../../assets/svg/logo.svg';
import lock from '../../assets/img/lock.png';
import netflixGift from '../../assets/img/Netflix-gift.png';
import visa from '../../assets/img/visa.png';
import mastercard from '../../assets/img/mastercard.png';
import amex from '../../assets/img/amex.png';
import elo from '../../assets/img/elo.png';
import hipercard from '../../assets/img/hipercard.png';
import arrowRight from '../../assets/svg/arrow-right.svg';
import secureBadge from '../../assets/svg/secureBadge.svg';

import { useEffect } from 'react';
import { Link, useNavigate } from "react-router-dom";

import { savePaymentType } from '@Infrastructure/stores/redux/features/signupDataSlice';
import { StoreState } from '@Infrastructure/stores/redux/config';
import { useDispatch, useSelector } from 'react-redux';

import { PaymentType } from '@Model/types/PaymentType';

import { motion } from 'framer-motion';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


export default function SignupPaymentPickerPage(): JSX.Element{
	
    //  ############# Redirecionamento de página ##################
    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);
    const signup = useSelector((state: StoreState) => state.signup);

    useEffect(()=>{
        if(user.data?.account?.active){
            navigate("/contents");
        }
        else if( !(signup.email && signup.password) && !user.data){ //página anterior, se não tem email e senha e nao tem usuario logado
            toast.error("Alguns dos seus dados estão faltando, você terá que reiniciar o processo.", {
                position: "top-center",
                hideProgressBar: false
            });
            setTimeout(()=>navigate("/signup/registration"), 2000);
        }
        else if(signup.plan===null){ //página de plan, se nao tem os dados de plan
            toast.error("Alguns dos seus dados estão faltando, você terá que reiniciar o processo.", {
                position: "top-center",
                hideProgressBar: false
            });
            setTimeout(()=>navigate("/signup/planform"), 2000);
        }
    }, []);


    //  ############# Manipulação de dados na view ##################
    const dispatch = useDispatch();

    function setPayment(paymentType: PaymentType){
        dispatch(savePaymentType(paymentType));
    }


    //  ############# Renderização do conteúdo ##################
    const width = window.innerWidth;

    return (
        <motion.div 
            className="SignupPaymentPickerPage"
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

                <div className='PaymentPicker-container'>

                    <img 
                        className='Icon' 
                        src={lock}
                        alt='lock icon'
                    />

                    <span className='Step-info'>PASSO 3 DE 3</span>

                    <h1 className='Title'>Escolha como você quer pagar</h1>

                    <p className='Description'>
                        Sua forma de pagamento está criptografada e você pode mudá-la quando quiser.
                    </p>

                    <p className='Description Benefits'>
                        Segurança e tranquilidade.
                    </p>

                    <p className='Description Benefits'>
                        Cancele online com facilidade.
                    </p>

                    <div className='Criptography'>
                        Criptografia de ponta a ponta
                        <img
                            alt="secure badge"
                            src={secureBadge}
                        />
                    </div>

                    <Link to="/signup/informations" onClick={()=>setPayment(PaymentType.credit_card)}>
                        <div className='Payment-info-container'>
                            <div className='Column-1'>
                                <div className='Title'>
                                    Cartão de crédito ou débito
                                </div>
                                <div className='Cards-icons'>
                                    <img 
                                        className='NetflixCard-icon'
                                        alt='Netflix gift card icon'
                                        src={visa}
                                    />
                                    <img 
                                        className='NetflixCard-icon'
                                        alt='Netflix gift card icon'
                                        src={mastercard}
                                    />
                                    <img 
                                        className='NetflixCard-icon'
                                        alt='Netflix gift card icon'
                                        src={amex}
                                    />
                                    <img 
                                        className='NetflixCard-icon'
                                        alt='Netflix gift card icon'
                                        src={elo}
                                    />
                                    <img 
                                        className='NetflixCard-icon'
                                        alt='Netflix gift card icon'
                                        src={hipercard}
                                    />
                                </div>
                            </div>

                            <div className='Column-2'>
                                <img 
                                    alt='arrow right icon'
                                    src={arrowRight}
                                />
                            </div>
                        </div>
                    </Link>

                    <Link to="/signup/informations" onClick={()=>setPayment(PaymentType.prepaid_card)}>
                        <div className='Payment-info-container'>

                            <div className='Column-1 Inline'>
                                <div className='Title'>
                                    Código do cartão pré-pago
                                </div>

                                <img 
                                    className='NetflixCard-icon'
                                    alt='Netflix gift card icon'
                                    src={netflixGift}
                                />
                            </div>

                            <div className='Column-2'>
                                <img 
                                    alt='arrow right icon'
                                    src={arrowRight}
                                />
                            </div>
                        </div>
                    </Link>

                    <Link to="/signup/informations" onClick={()=>setPayment(PaymentType.debit_card)}>
                        <div className='Payment-info-container'>

                            <div className='Column-1 Inline'>
                                <div className='Title'>
                                    Pix
                                </div>

                                <img 
                                    className='NetflixCard-icon'
                                    alt='Netflix gift card icon'
                                    src={netflixGift}
                                />
                            </div>

                            <div className='Column-2'>
                                <img 
                                    alt='arrow right icon'
                                    src={arrowRight}
                                />
                            </div>
                        </div>
                    </Link>

                </div>

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