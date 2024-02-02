import { useEffect } from "react";

export default function CheckReRendering() {
    useEffect(()=>{
        console.log("rerendered");
    }, []);
    return <></>;
}