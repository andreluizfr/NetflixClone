import React, {useRef} from 'react';

interface TooltipProps extends React.HTMLProps<HTMLDivElement>{
    contentText: string;
    contentSize: 'small' | 'medium' | 'large';
};

export default function Tooltip ({children, contentText, contentSize, ...rest}: TooltipProps): JSX.Element {

    const sizeClasses = {
        small: 'after:top-[-24px] after:left-0 after:text-[12px] after:leading-[12px] after:p-[6px] after:rounded-[2px] before:w-[4px] before:h-[4px] before:top-[-2px] before:left-[50%]',
        medium: 'after:top-[-32px] after:left-0 after:text-[16px] after:leading-[16px] after:p-[8px] after:rounded-[4px] before:w-[8px] before:h-[8px] before:top-[-4px] before:left-[50%]',
        large: 'after:top-[-56px] after:left-0 after:text-[24px] after:leading-[24px] after:p-[12px] after:rounded-[6px] before:w-[16px] before:h-[16px] before:top-[-16px] before:left-[50%]',
    };

    const sizeClass = sizeClasses[contentSize] || '';

    const ref = useRef<HTMLDivElement | null>(null);
    if(ref?.current)
        ref.current.dataset.content = contentText;

    return (
        <div
            className={`
                relative
                hover:after:inline
                after:hidden
                after:content-[attr(data-content)]
                after:absolute
                after:bg-white
                after:text-gray-950
                after:z-50
                after:whitespace-nowrap
                after:font-normal

                hover:before:inline
                before:hidden
                before:absolute
                before:bg-white
                before:z-50
                before:rotate-45
                ${sizeClass}
            `}
            {...rest}
            ref={ref}
        >
            {children}
        </div>
    );
}