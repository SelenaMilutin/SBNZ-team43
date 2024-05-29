import ButtonStyle from "./Button.style";


type ButtonType = 'submit' | 'button' | 'reset';

interface ButtonProps {
    text: string;
    type: ButtonType;
    onClickHandler: () => void;
}


const Button = ({text, type, onClickHandler}: ButtonProps) => {
    return <>
        <ButtonStyle onClick={onClickHandler} type={type}>{text}</ButtonStyle>
    </>
}

export default Button;