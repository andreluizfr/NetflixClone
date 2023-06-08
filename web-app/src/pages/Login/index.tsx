import './styles.css';
import AnimatedInput from '../../components/AnimatedInput';
import logo from '../../assets/svg/logo.svg';

export default function Login(): JSX.Element{

    return (
        <div className="LoginPage">
            <div className="Background-filter">

                <header className='Header'>
                    <a href="/">
                        <img className='Logo' src={logo} alt="Netflix Logo"/>
                    </a>
                </header>

                <form className='Login-form'>
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

                    <button className='Login-button'>
                        Entrar
                    </button>

                    <div className='Remember-me-and-need-help-container'>
                        <div className='Remember-me-checkbox'>
                            <label>
                                <input type='checkbox' required/>
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