import './styles.css';
import logo from '../../assets/svg/logo.svg';

export default function Home(): JSX.Element{
    
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

                        <button className="Login-button" onClick={()=>window.location.href="http://localhost:5173/login"}>
                            Entrar
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
                            <button className='Subscribe-button'>{"Vamos lá >"}</button>
                        </div>
                    </div>
                </main>
                
            </div>
        </div>
    );
}