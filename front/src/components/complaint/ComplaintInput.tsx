import { InputDiv, Label } from "../shared/Input/Input.style";
import { ComplaintInputStyle } from "./ComplaintInputStyle";

type InputType = 'text' | 'number' | 'file' | 'date' | 'datetime-local' | 'password';

interface InputProps {
    label: string;
    inputType: InputType;
    placeholder?: string;
    defaultValue?: string | number;
    setValue: (event: any) => void;
}


const ComplaintInput = ({label, inputType, placeholder, defaultValue, setValue}: InputProps) => {
    return (<InputDiv>
                <Label>{label}</Label>
                <ComplaintInputStyle 
                    placeholder={placeholder || ""} 
                    defaultValue={defaultValue || ""}
                    onChange={(e) => setValue(e.target.value)}/>
            </InputDiv>)
}

export default ComplaintInput;