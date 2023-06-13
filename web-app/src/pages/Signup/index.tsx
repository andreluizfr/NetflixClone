import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';
import checkmark from '../../assets/img/checkmark.png';
import checkmarkGroup from '../../assets/svg/checkmark-group.svg';

import { Link } from "react-router-dom";
import { useState } from 'react';

export default function SignupPage(): JSX.Element {

    const [step, setStep] = useState(1);

    function goToNextStep(){
        setStep(current=>current+1);
    }

    return (
        <div className='SignupPage'>
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
                    <form className='Signup-container-1'>

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

                    </form>
                }

                {step===2 &&
                    <form className='Signup-container-2'>

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

                        <button className='NextStep-button'>
                            <Link to="/signup/planform">
                                Próximo
                            </Link>
                        </button>

                    </form>
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

        </div>
    );
}