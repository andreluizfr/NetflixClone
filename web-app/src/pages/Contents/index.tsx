import './styles.css';
import logo from '../../assets/svg/logo.svg';
import playButton from '../../assets/svg/play-button.svg';
import infoCircle from '../../assets/svg/info-circle.svg';
import searchIcon from '../../assets/img/search-icon.png';
import bellIcon from '../../assets/img/bell-icon.png';
import TheWitcher3Preview from '../../assets/video/the-witcher-3-preview.mp4';

import ProfileDropdownMenu from '../../components/ProfileDropdownMenu';

import { Link } from 'react-router-dom';
import { Helmet } from 'react-helmet-async';
import { motion } from 'framer-motion';

export default function ContentsPage(): JSX.Element {

    const height = window.innerHeight;
    const width = window.innerWidth;

    return (
        <motion.div className='ContentsPage'>
            <Helmet>
                <link rel="preconnect" href="https://fonts.googleapis.com"/>
                <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous"/>
                <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap" rel="stylesheet"/>
                <meta property="og:title" content="Netflix Contents" />
                <meta property="og:url" content="http://localhost:5173/contents" />
                <meta property="og:image" content={logo} />
                <meta property="og:image:alt" content="Netflix logo" />
                <meta property="og:description" content="Watch the best Movies, Tv Series and Animes" />
                <meta property="og:site_name" content="Netflix" />
            </Helmet>

            <header className='Header'>
                <div className='Logo-container'>
                    <Link to="/contents">
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

                <span className='Extra-space'/>

                <nav className='Other-options-container'>
                    <img src={searchIcon} className='Search-icon' alt="search icon" />
                    <img src={bellIcon} className='Notifications-icon' alt='Notifications-icon'/>
                    <ProfileDropdownMenu/>
                </nav>
            </header>

            <motion.aside 
                className='Work-preview'
                initial={{y: -(height/2), opacity: 0}}
                animate={{y: 0, opacity: 1, transition:{type: "easeOut", duration: 0.6}}}
            >
                <video className='Background-video' muted autoPlay loop>
                    <source src={TheWitcher3Preview} type="video/mp4"/>
                </video>
                <div className='Work-preview-menu'>
                    <h1 className='Title'>
                        The Witcher: temporada 3
                    </h1>
                    <div className='Toolbar'>
                        <button className='Button'>
                            <img src={playButton} alt="play icon"/>
                            Assistir
                        </button>

                        <button className='Button'>
                            <img src={infoCircle} alt="info circle icon"/>
                            Mais informações
                        </button>
                    </div>
                </div>
            </motion.aside>

            <main className='Contents-container'>

                <section className='Section'>
                    <h1 className='Title'>
                        Continuar assistindo como {"perfil1"}
                    </h1>
                    <motion.div 
                        className='Playlist'
                        initial={{x: -(width/2), opacity: 0}}
                        animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    >
                        {   
                            (new Array(20)).fill(0).map((value, index)=>
                                <img 
                                    src="https://occ-0-1722-2773.1.nflxso.net/dnm/api/v6/6gmvu2hxdfnQ55LZZjyzYR4kzGk/AAAABdzOeOqPEk1M_DWfMF64utFLlsrEMLTsiViL_B4cVd1PuKFzMNbkkathcJBDLghK2pIeAanGHGL_X5pgpNZDnCQPlbK9mVM8UFdjutj-fHXxQASUlsEWhvy25GXqlXGZVz_3.jpg?r=fb8" 
                                    className='Thumbnail'
                                    key={"work-"+index}
                                />
                            )
                        }
                    </motion.div>
                </section>

                <section className='Section'>
                    <h1 className='Title'>
                        Continuar assistindo como {"perfil1"}
                    </h1>
                    <motion.div 
                        className='Playlist'
                        initial={{x: -(width/2), opacity: 0}}
                        animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    >
                        {   
                            (new Array(20)).fill(0).map((value, index)=>
                                <img 
                                    src="https://occ-0-1722-2773.1.nflxso.net/dnm/api/v6/6gmvu2hxdfnQ55LZZjyzYR4kzGk/AAAABdzOeOqPEk1M_DWfMF64utFLlsrEMLTsiViL_B4cVd1PuKFzMNbkkathcJBDLghK2pIeAanGHGL_X5pgpNZDnCQPlbK9mVM8UFdjutj-fHXxQASUlsEWhvy25GXqlXGZVz_3.jpg?r=fb8" 
                                    className='Thumbnail'
                                    key={"work-"+index}
                                />
                            )
                        }
                    </motion.div>
                </section>

                <section className='Section'>
                    <h1 className='Title'>
                        Continuar assistindo como {"perfil1"}
                    </h1>
                    <motion.div 
                        className='Playlist'
                        initial={{x: -(width/2), opacity: 0}}
                        animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    >
                        {   
                            (new Array(20)).fill(0).map((value, index)=>
                                <img 
                                    src="https://occ-0-1722-2773.1.nflxso.net/dnm/api/v6/6gmvu2hxdfnQ55LZZjyzYR4kzGk/AAAABdzOeOqPEk1M_DWfMF64utFLlsrEMLTsiViL_B4cVd1PuKFzMNbkkathcJBDLghK2pIeAanGHGL_X5pgpNZDnCQPlbK9mVM8UFdjutj-fHXxQASUlsEWhvy25GXqlXGZVz_3.jpg?r=fb8" 
                                    className='Thumbnail'
                                    key={"work-"+index}
                                />
                            )
                        }
                    </motion.div>
                </section>

                <section className='Section'>
                    <h1 className='Title'>
                        Continuar assistindo como {"perfil1"}
                    </h1>
                    <motion.div 
                        className='Playlist'
                        initial={{x: -(width/2), opacity: 0}}
                        animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    >
                        {   
                            (new Array(20)).fill(0).map((value, index)=>
                                <img 
                                    src="https://occ-0-1722-2773.1.nflxso.net/dnm/api/v6/6gmvu2hxdfnQ55LZZjyzYR4kzGk/AAAABdzOeOqPEk1M_DWfMF64utFLlsrEMLTsiViL_B4cVd1PuKFzMNbkkathcJBDLghK2pIeAanGHGL_X5pgpNZDnCQPlbK9mVM8UFdjutj-fHXxQASUlsEWhvy25GXqlXGZVz_3.jpg?r=fb8" 
                                    className='Thumbnail'
                                    key={"work-"+index}
                                />
                            )
                        }
                    </motion.div>
                </section>

                <section className='Section'>
                    <h1 className='Title'>
                        Continuar assistindo como {"perfil1"}
                    </h1>
                    <motion.div 
                        className='Playlist'
                        initial={{x: -(width/2), opacity: 0}}
                        animate={{x: 0, opacity: 1, transition:{type: "spring", bounce: 0.3, duration: 2}}}
                    >
                        {   
                            (new Array(20)).fill(0).map((value, index)=>
                                <img 
                                    src="https://occ-0-1722-2773.1.nflxso.net/dnm/api/v6/6gmvu2hxdfnQ55LZZjyzYR4kzGk/AAAABdzOeOqPEk1M_DWfMF64utFLlsrEMLTsiViL_B4cVd1PuKFzMNbkkathcJBDLghK2pIeAanGHGL_X5pgpNZDnCQPlbK9mVM8UFdjutj-fHXxQASUlsEWhvy25GXqlXGZVz_3.jpg?r=fb8" 
                                    className='Thumbnail'
                                    key={"work-"+index}
                                />
                            )
                        }
                    </motion.div>
                </section>

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

        </motion.div>  
    )
}