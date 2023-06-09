import './styles.css';
import { InputHTMLAttributes, useEffect, useRef } from "react";

interface Props extends InputHTMLAttributes<HTMLInputElement> {
    title?: string;
    warning?: string | undefined;
    hasShow?: boolean | undefined;
    theme?: "light" | "dark";
}

export default function AnimatedInput(props:Props): JSX.Element {

    //desestruturação para pegar as props passadas para o component e o resto das props são repassadas para tag input
    const {title, warning, hasShow, theme, ...rest} = props;

    const ref = useRef <HTMLInputElement | null>(null);

    //inicia a tag input com atributo wasBlured como falso, e o tema vindo dos parametros
    useEffect(()=>{
        if(ref.current){
            ref.current.setAttribute("wasBlured", "false");
            if(theme)
                ref.current.setAttribute("theme", theme);
        }
    }, []);

    //ao perder o foco do input muda o atributo wasBlured para true
    function wasBlured(event: React.FocusEvent<HTMLInputElement>){
        event.target.setAttribute("wasBlured", "true");
    }

    function toggleShow(event: React.MouseEvent<HTMLElement>){
        const animatedInput = (event.target as HTMLElement).parentElement;
        const input = ref.current as HTMLInputElement;

        const show = animatedInput?.getElementsByClassName("Show")[0] as HTMLSpanElement;
        
        if(input.getAttribute("type") === "password"){
            input.setAttribute("type", "text");
            show.innerText = "OCULTAR";
        } else if(input.getAttribute("type") === "text"){
            input.setAttribute("type", "password");
            show.innerText = "MOSTRAR";
        }
    }

    //se foi passada a propriedade warning, coloca a tag p com o warning
    return (
        <div className='Animated-input'>
            <input {...rest} ref={ref} onBlur={wasBlured} className="Input"/>
            {title?
                <span className='Title'>{title}</span>
                :
                null
            }
            {warning?
                <p className='Warning'>{warning}</p>
                :
                null
            }
            {hasShow?
                <span className='Show' onClick={toggleShow}>
                    MOSTRAR
                </span>
                :
                null
            }
 
        </div>
    );

}