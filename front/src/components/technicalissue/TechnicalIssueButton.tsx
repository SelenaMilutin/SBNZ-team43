import { TechnicalIssueButtonStyle } from "./TechnicalIssueCardStyle";


type ButtonType = 'submit' | 'button' | 'reset';

interface ButtonProps {
    text: string;
    type: ButtonType;
    onClickHandler: () => void;
}


const TechnicalIssueButton = ({text, type, onClickHandler}: ButtonProps) => {

    return <TechnicalIssueButtonStyle onClick={onClickHandler} type={type}>{text}</TechnicalIssueButtonStyle>
    
}

export default TechnicalIssueButton;