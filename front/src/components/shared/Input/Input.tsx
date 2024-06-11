import StyledInput, { InputDiv, Label } from "./Input.style";

type InputType = 'text' | 'number' | 'file' | 'date' | 'datetime-local' | 'password';

interface InputProps {
    label: string;
    inputType: InputType;
    placeholder?: string;
    defaultValue?: string | number;
    setValue: (event: any) => void;
}


const Input = ({label, inputType, placeholder, defaultValue, setValue}: InputProps) => {
    return (<InputDiv>
                <Label>{label}</Label>
                <StyledInput 
                    type={inputType} 
                    placeholder={placeholder || ""} 
                    defaultValue={defaultValue || ""}
                    onChange={(e) => setValue(e.target.value)}></StyledInput>
            </InputDiv>)
}

export default Input;