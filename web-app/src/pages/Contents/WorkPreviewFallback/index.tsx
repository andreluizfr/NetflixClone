import './styles.css';

import DotLoader from "react-spinners/DotLoader";

export default function WorkPreviewFallback (): JSX.Element {
    return (
        <aside  className='Work-preview-fallback'>
            <DotLoader
                className='Loader'
                color="rgb(229,9,20)"
                size={70}
                aria-label="Loading Spinner"
                data-testid="loader"
            />
        </aside>
    );
}