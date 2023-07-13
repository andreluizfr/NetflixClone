import './styles.css';
import AnimatedInput from '@Presentation/components/AnimatedInput';
import logo from '@Presentation/assets/svg/logo.svg';

import { LoginImpl } from '@Application/useCases/Login/LoginImpl';

import { StoreState } from '@Infrastructure/stores/redux/config';

import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


export default function Login(): JSX.Element{

    const navigate = useNavigate();
    const user = useSelector((state: StoreState) => state.user);

    const [email, setEmail] = useState<string | null>(null);
    const [password, setPassword] = useState<string | null>(null);

    const loginResult = LoginImpl(email, password);

    function onSubmit (e: React.FormEvent){
        e.preventDefault();

        const inputs = document.getElementsByClassName("Input") as HTMLCollection;

        setEmail((inputs[0] as HTMLInputElement).value);
        setPassword((inputs[1] as HTMLInputElement).value);
    }


    //botão pra logar foi pressionado, então estados
    useEffect(()=>{
        if(email && password){
            loginResult.refetch();
            setEmail(null);
            setPassword(null);
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [email, password]);


    //já está possivelmente logado
    useEffect(()=>{
        if(user.data && localStorage.getItem("x-access-token"))
            navigate("/contents");
    }, [navigate, user.data]);


    //manipulador da view com a resposta do login
    useEffect(()=>{
        if(loginResult.isError && loginResult.error)
            toast(loginResult.error.httpStatusCode+": "+loginResult.error.message);
        else if(loginResult.data?.data)
            toast("Login realizado com sucesso, você será redirecionado em breve.");
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [loginResult.isError, loginResult.data, loginResult.error]);


    return (
        <div className="LoginPage">
            <div className="Background-filter">

                <header className='Header'>
                    <a href="/">
                        <img className='Logo' src={logo} alt="Netflix Logo"/>
                    </a>
                </header>

                <div className='Login-background'>
                    <form className='Login-form' onSubmit={onSubmit}>
                        <h1 className='Title'>Entrar</h1>

                        <AnimatedInput 
                            title='Email ou número de telefone'
                            warning='Informe um email ou número de telefone válido.'
                            hasShow={false}
                            type='email'
                            required
                        />

                        <AnimatedInput 
                            title='Senha' 
                            warning='A senha deve ter entre 4 e 60 caracteres.'
                            hasShow={true}
                            type='password'
                            minLength={4}
                            maxLength={60}
                            required
                        />

                        <button className='Login-button' type='submit'>
                            Entrar
                        </button>

                        <div className='Remember-me-and-need-help-container'>
                            <div className='Remember-me-checkbox'>
                                <label>
                                    <input type='checkbox'/>
                                    <span/>
                                </label>

                                <span>
                                    Lembre-se de mim
                                </span>
                            </div>

                            <span className='Need-help'>
                                Precisa de ajuda?
                            </span>
                        </div>

                        <div className='Aditional-infos-container'>
                            <p className='New-members'>
                                Novo por aqui? <strong>Assine agora</strong>.
                            </p>
                            <p className='Recaptcha-notice'>
                                Esta página é protegida pelo Google reCAPTCHA
                                para garantir que você não é um robô. <a href="#">Saiba mais.</a>
                            </p>
                        </div>
                    </form>
                </div>

                <footer>
                    <div className='Row-1'>
                        Dúvidas? Ligue <a href="#">0800 591 8942</a>
                    </div>

                    <div className='Row-2'>
                        <a href="#">Perguntas frequentes</a>
                        <a href="#">Central de Ajuda</a>
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
            <ToastContainer/>
        </div>
    );
}