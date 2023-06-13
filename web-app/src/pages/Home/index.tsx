import './styles.css';
import logo from '../../assets/svg/logo.svg';
import { Link } from "react-router-dom";

import { useDispatch } from 'react-redux';
import { saveEmail } from '../../store/features/signupSlice';

export default function Home(): JSX.Element{

    const dispatch = useDispatch();

    function saveEmailToSignup(e: React.MouseEvent<HTMLAnchorElement>){

        const input = document.getElementsByClassName("Input-email")[0] as HTMLInputElement;
        const email = input?.value;

        if(email === null) 
            e.preventDefault();
        else 
            dispatch(saveEmail(email));
            
    }

    return(
        <div className='HomePage'>
            <div className='Background-gradient'>
                    
                <header className='Header'>
                    <div className='Logo-container'>
                        <img className='Logo' src={logo} alt="Netflix Logo"/>
                    </div>

                    <div className='Language-container'>
                        <select className="Select-language" defaultValue={"pt"}>
                            <option value="pt">Português</option>
                            <option value="en">English</option>
                        </select>

                        <button className="Login-button">
                            <Link to="/login">Entrar</Link>
                        </button>
                    </div>
                </header>

                <main className='HomePage-content'>
                    <div className='Content'>
                        <div className='Row-1'>
                            Filmes, séries e muito mais, sem limites
                        </div>
                        <div className='Row-2'>
                            Assista onde quiser. Cancele quando quiser.
                        </div>
                        <div className='Row-3'>
                            Quer assistir? Informe seu email para criar ou reiniciar sua assinatura.
                        </div>
                        <div className='Row-4'>
                            <input className='Email-input' placeholder='Email'/>
                            <button className='Subscribe-button'>
                                <Link to='/signup' onClick={saveEmailToSignup}>
                                    {"Vamos lá >"}
                                </Link>
                            </button>
                        </div>
                    </div>
                </main>

                <footer>
                    <div className='Row-1'>
                        Dúvidas? Ligue <a href="#">0800 591 8942</a>
                    </div>

                    <div className='Row-2'>
                        <a href="#">Perguntas frequentes</a>
                        <a href="#">Central de Ajuda</a>
                        <a href="#">Conta</a>
                        <a href="#">Media Center</a>
                        <a href="#">Relações com investidores</a>
                        <a href="#">Resgatar cartão pré-pago</a>
                        <a href="#">Comprar cartão pré-pago</a>
                        <a href="#">Formas de assistir</a>
                        <a href="#">Termos de Uso</a>
                        <a href="#">Privacidade</a>
                        <a href="#">Preferências de cookies</a>
                        <a href="#">Informações corporativas</a>
                        <a href="#">Entre em contato</a>
                        <a href="#">Teste de velocidade</a>
                        <a href="#">Avisos legais</a>
                        <a href="#">Só na Netflix</a>
                    </div>
                    
                    <div className='Row-3'>
                        <select className="Select-language" defaultValue={"pt"}>
                            <option value="pt">Português</option>
                            <option value="en">English</option>
                        </select>
                    </div>

                    <div className='Row-4'>
                        Netflix Brasil
                    </div>
                </footer>
                
            </div>
        </div>
    );
}