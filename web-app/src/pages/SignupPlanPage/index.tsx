import './styles.css';
import logo from '../../assets/svg/logo.svg';
import devices from '../../assets/img/devices.png';
import checkmarkGroup from '../../assets/svg/checkmark-group.svg';
import planGridBoolean from '../../assets/svg/planGrid-boolean.svg';

import { useState } from 'react';
import { Link } from 'react-router-dom';

enum Plan{
    DefaultWithAds,
    Default,
    Premium
}

export default function SignupPlanPage(): JSX.Element{

    const [plan, setPlan] = useState<Plan>(Plan.DefaultWithAds);

    function changePlan(plan: Plan){
        setPlan(plan);
    }

    return (
        <div className='SignupPlanPage'>

            <header className='Header'>
                <a href="/">
                    <img className='Logo' src={logo} alt="Netflix Logo"/>
                </a>

                <a className='Login-link' href="/login">Entrar</a>
            </header>

            <main className='BestPlan-container'>

                <span className='Step-info'>PASSO 1 DE 3</span>

                <h1 className='Title'>Escolha o melhor plano para você</h1>

                <div className='Advantages'>
                    <img 
                        className='Icon'
                        src={checkmarkGroup}
                        alt='checkmark icon'
                    />
                    <p className='Text'>Assista o quanto quiser.</p>
                </div>

                <div className='Advantages'>
                    <img 
                        className='Icon'
                        src={checkmarkGroup}
                        alt='checkmark icon'
                    />
                    <p className='Text'>Recomendações especiais para você.</p>
                </div>

                <div className='Advantages'>
                    <img 
                        className='Icon'
                        src={checkmarkGroup}
                        alt='checkmark icon'
                    />
                    <p className='Text'>Altere ou cancele seu plano quando quiser.</p>
                </div>
            
                <table className='Plans-table'>
                    <tbody>
                        <tr>
                            <td>Preço por mês</td>
                            <td>R$18,90</td>
                            <td>R$39,90</td>
                            <td>R$55,90</td>
                        </tr>
                        <tr>
                            <td>Qualidade do vídeo</td>
                            <td>Ótima</td>
                            <td>Ótima</td>
                            <td>Excepcional</td>
                        </tr>
                        <tr>
                            <td>Resolução</td>
                            <td>1080p</td>
                            <td>1080p</td>
                            <td>4K+HDR</td>
                        </tr>
                        <tr>
                            <td>Assista na TV, computador, celular ou tablet</td>
                            <td><img src={checkmarkGroup} className='Chechmark-group' alt="checkmark group icon"/></td>
                            <td><img src={checkmarkGroup} className='Chechmark-group' alt="checkmark group icon"/></td>
                            <td><img src={checkmarkGroup} className='Chechmark-group' alt="checkmark group icon"/></td>
                        </tr>
                        <tr>
                            <td>Downloads</td>
                            <td><img src={planGridBoolean} className='Chechmark-group' alt="checkmark group icon"/></td>
                            <td><img src={checkmarkGroup} className='Chechmark-group' alt="checkmark group icon"/></td>
                            <td><img src={checkmarkGroup} className='Chechmark-group' alt="checkmark group icon"/></td>
                        </tr>
                    </tbody>
                </table>

                <div className='Center'>
                    <button className='AllPlans-button'>
                        Ver todos os planos
                    </button>
                </div>

                <div className='Disclaimers'>

                    <p>
                        Ao selecionar um plano com anúncios, você precisará fornecer sua data de nascimento para a personalização de anúncios e outras finalidades consistentes com a <a href="#">Declaração de Privacidade</a> da Netflix.
                    </p>
                        
                    <p>
                    A disponibilidade das opções HD (720p), Full HD (1080p), Ultra HD (4K) e HDR está sujeita ao serviço de internet e à funcionalidade do aparelho. Nem todo conteúdo está disponível em todas as resoluções. Para obter mais detalhes, consulte os nossos <a href="#">Termos de Uso</a>.
                    </p>

                    <p>
                        Só pessoas que moram com você podem usar sua conta. Adicione 1 assinante extra com o plano Padrão ou até 2 com o plano Premium. <a href="#">Saiba mais</a>. Assista em 4 aparelhos ao mesmo tempo com o Premium, em 2 aparelhos com o Padrão ou o Padrão com anúncios e em 1 com o Básico.
                    </p>

                </div>

                <div className='Center'>
                    <button className='NextStep-button'>
                        <Link to="/signup/registration">
                            Próximo
                        </Link>
                    </button>
                </div>

            </main>

            <footer>
                <div className='Row-1'>
                    Dúvidas? Ligue <a href="#">0800 591 8942</a>
                </div>

                <div className='Row-2'>
                    <a href="#">Perguntas frequentes</a>
                    <a href="#">Central de Ajuda</a>
                    <a href="#">Netflix Shop</a>
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
    );
}