import './styles.scss';
import AnimatedInput from '@Presentation/components/AnimatedInput';
import logo from '@Presentation/assets/svg/logo.svg';

import { LoginService } from '@Services/Login/LoginService';

import { useEffect, useState } from 'react';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import { Helmet } from 'react-helmet-async';


export default function Login(): JSX.Element{

    //  ############# Manipulação de requisição ##################
    const [email, setEmail] = useState<string | null>(null);
    const [password, setPassword] = useState<string | null>(null);

    const loginResult = LoginService();

    useEffect(()=>{
        if(email && password){
            loginResult.mutate({email, password});
            setEmail(null);
            setPassword(null);
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [email, password]);

    useEffect(()=>{

        if(loginResult.isError && loginResult.error)
            toast.error(loginResult.error.httpStatusCode+": "+loginResult.error.message, {
                position: "top-center",
                hideProgressBar: true
            });

        else if(loginResult.data?.data)
            toast.success("Login realizado com sucesso, você será redirecionado em breve.", {
                position: "top-right",
                hideProgressBar: false
            });
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [loginResult.isError, loginResult.data, loginResult.error]);


    //  ############# Manipulação de dados na view ##################
    function onSubmit (e: React.FormEvent){
        e.preventDefault();

        const inputs = document.getElementsByClassName("Input") as HTMLCollection;

        setEmail((inputs[0] as HTMLInputElement).value);
        setPassword((inputs[1] as HTMLInputElement).value);
    }


    //  ############# Renderização do conteúdo ##################
    return (
        <div className="LoginPage">

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
                hideProgressBar={false}
                theme="dark"
            />
            
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
        </div>
    );
}