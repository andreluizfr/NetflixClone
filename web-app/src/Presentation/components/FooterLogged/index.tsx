import './styles.css';

export default function Footer(): JSX.Element {

    //  ############# Renderização do conteúdo ##################
    return(
        <footer className='Footer'>
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
    );
}