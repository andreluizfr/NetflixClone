import './styles.css';
import logo from '../../../assets/svg/logo.svg';
import searchIcon from '../../../assets/img/search-icon.png';
import bellIcon from '../../../assets/img/bell-icon.png';

import ProfileDropdownMenu from '../../../components/ProfileDropdownMenu';

import { Link } from 'react-router-dom';
import { useMediaQuery } from 'react-responsive';

export default function Header({headerRef}: {headerRef: React.MutableRefObject<null>}): JSX.Element {

    const isLarge = useMediaQuery({ query: '(max-width: 956px)' });

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

        <nav className='Other-options-container'>
            <img src={searchIcon} className='Search-icon' alt="search icon" />
            <img src={bellIcon} className='Notifications-icon' alt='Notifications-icon'/>
            <ProfileDropdownMenu/>
        </nav>
    </header>
    )
}