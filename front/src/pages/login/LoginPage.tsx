import { useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import { LoginPageStyle } from "./LoginPageStyle";
import Button from "../../components/shared/Button/Button";
import Input from "../../components/shared/Input/Input";
import { AuthService } from "../../services/AuthService";

const LoginPage = () => {

    const { onLogin } = useAuth();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    
    function onLoginClick() {

        AuthService.login(username, password)
        .then( (response) => {
            console.log(response)
            onLogin(response.data)
        })
        .catch( (error) => {
            console.log(error)
        })

    }

    return (
        <LoginPageStyle>
            <Input inputType="text" label="Email" setValue={setUsername}></Input>
            <Input inputType="password" label="Password" setValue={setPassword}></Input>
            <Button text="Login" type="submit" onClickHandler={onLoginClick}/>
        </LoginPageStyle>
    )

}

export default LoginPage;