import './styles.css';
import facebookIcon from '@Presentation/assets/svg/social/facebook.svg';
import instagramIcon from '@Presentation/assets/svg/social/instagram.svg';
import twitterIcon from '@Presentation/assets/svg/social/twitter.svg';
import youtubeIcon from '@Presentation/assets/svg/social/youtube.svg';

export default function Footer(): JSX.Element {

    //  ############# Renderização do conteúdo ##################
    return(
        <footer className='Footer'>
            <div className='Social-links'>
                <a className="Social-link" href="https://www.facebook.com/netflixbrasil" target="_blank" aria-label="facebook">
                    <img src={facebookIcon} alt="facebook icon"/>
                </a>
                <a className="Social-link" href="https://www.instagram.com/NetflixBrasil" target="_blank" aria-label="instagram">
                    <img src={instagramIcon} alt="instagram icon"/>
                </a>
                <a className="Social-link" href="https://twitter.com/NetflixBrasil" target="_blank" aria-label="twitter">
                    <img src={twitterIcon} alt="twitter icon"/>
                </a>
                <a className="Social-link" href="https://www.youtube.com/user/NetflixBRA" target="_blank" aria-label="youtube">
                    <img src={youtubeIcon} alt="youtube icon"/>
                </a>
            </div>

            <ul className='Member-footer-links'>
                <li className='Member-footer-link-wrapper'><a href="#">Audiodescrição</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Central de Ajuda</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Cartão pré-pago</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Imprensa</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Relações com investidores</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Carreiras</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Termos de uso</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Privacidade</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Avisos legais</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Preferências de cookies</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Informações corporativas</a></li>
                <li className='Member-footer-link-wrapper'><a href="#">Entre em contato</a></li>
            </ul>
            
            <div className='Member-footer-service'>
                <button className="Service-code">Código do serviço</button>
            </div>

            <div className='Member-footer-copyright'>
                © 1997-2023 Netflix, Inc.  
            </div>
        </footer>
    );
}