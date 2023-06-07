import './styles.css';
import logo from '../../assets/svg/logo.svg';

export default function Login(): JSX.Element{

    return (
        <div className="LoginPage">
            <header className='Header'>
                <img className='Logo' src={logo} alt="Netflix Logo"/>
            </header>

            <section>
                <form className='Login-form'>

                </form>
            </section>

            <footer>
                <div className='Row-1'>

                </div>
                <div className='Row-2'>

                </div>
                <div className='Row-3'>

                </div>
            </footer>
        </div>
    );
}