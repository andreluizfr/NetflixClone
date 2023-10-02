import './styles.css';
import logo from '@Presentation/assets/svg/logo.svg';
import searchIcon from '@Presentation/assets/img/search-icon.png';
import bellIcon from '@Presentation/assets/img/bell-icon.png';

import ProfileDropdownMenu from '@Presentation/components/ProfileDropdownMenu';

import { StoreState } from '@Infrastructure/stores/redux/config';
import { useSelector } from 'react-redux';

import { Link } from 'react-router-dom';
import { useMediaQuery } from 'react-responsive';

export default function Header({headerRef}: {headerRef: React.MutableRefObject<null>}): JSX.Element {

    //  ############# Renderização do conteúdo ##################
    const isLarge = useMediaQuery({ query: '(max-width: 956px)' });

    const user = useSelector((state: StoreState) => state.user);

    return (
        <header className='Header' ref={headerRef}>
            {isLarge?
            <>  
                <nav className='Content-navigation Hamburguer-menu'>
                    <ul>
                        <li>Início</li>
                        <li>Séries</li>
                        <li>Filmes</li>
                        <li>Bombando</li>
                        <li>Minha lista</li>
                        <li>Navegar por idiomas</li>
                    </ul>
                </nav>

                <div className='Logo-container'>
                    <Link to="/">
                        <img className='Logo' src={logo} alt="Netflix Logo"/>
                    </Link> 
                </div>
            </>
            :
            <>
                <div className='Logo-container'>
                    <Link to="/">
                        <img className='Logo' src={logo} alt="Netflix Logo"/>
                    </Link> 
                </div>

                <nav className='Content-navigation'>
                    <ul>
                        <li>Início</li>
                        <li>Séries</li>
                        <li>Filmes</li>
                        <li>Bombando</li>
                        <li>Minha lista</li>
                        <li>Navegar por idiomas</li>
                    </ul>
                </nav>
            </>
        }

        <span className='Extra-space'/>

        {user.data?
            <nav className='Other-options-container'>
                <img src={searchIcon} className='Search-icon' alt="search icon" />
                <img src={bellIcon} className='Notifications-icon' alt='Notifications-icon'/>
                <ProfileDropdownMenu/>
            </nav>
            :
            <nav className='Other-options-container'>
                <button className='Signup-button'>
                    <Link to="/signup">
                        ASSINE A NETFLIX
                    </Link>
                </button>

                <button className='Login-button'>
                    <Link to="/login">
                        Entrar
                    </Link>
                </button>
            </nav>
        }
    </header>
    )
}