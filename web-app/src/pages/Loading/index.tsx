import './styles.css';

import DotLoader from "react-spinners/DotLoader";

export default function Loading() : JSX.Element {

    return(
        <main className='LoadingPage'>

            <DotLoader
                className='Loader'
                color="white"
                size={70}
                aria-label="Loading Spinner"
                data-testid="loader"
            />

        </main>
    );

}