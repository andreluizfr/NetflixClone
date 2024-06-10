import DotLoader from "react-spinners/DotLoader";
import { useMediaQuery } from 'react-responsive';

export default function LoadingSpinner() : JSX.Element {

    const width = window.innerWidth;

    const isSmall = useMediaQuery({
        query: '(max-width: 576px)'
    });

    const isMedium = useMediaQuery({
        query: '(max-width: 720px)'
    });

    const isLarge = useMediaQuery({
        query: '(max-width: 956px)'
    });

    return(
        <div className='LoadingSpinner' style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            width: width, 
            height: width/1.77 //manter proporção 16/9
        }}>

            <DotLoader
                className='Loader'
                color="rgb(229,9,20)"
                size={isSmall?(40):isMedium?(50):isLarge?(60):(70)}
                aria-label="Loading Spinner"
                data-testid="loader"
            />

        </div>
    );

}