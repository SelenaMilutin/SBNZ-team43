import { useState } from "react";
import { useAuth } from "../../contexts/AuthContext";
import Button from "../../components/shared/Button/Button";
import Input from "../../components/shared/Input/Input";
import { AuthService } from "../../services/AuthService";
import { RegisterPageStyle } from "./RegisterPageStyle";

const RegisterPage = () => {

    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    
    function onRegisterClick() {

        AuthService.register(username, password, name, lastName, phone, address)
        .then( (response) => {
            alert("Registered!")
        })
        .catch( (error) => {
            console.log(error)
        })

    }

    return (
        <RegisterPageStyle>
            <h2>Register</h2>
            <Input inputType="text" label="Email" setValue={setUsername}></Input>
            <Input inputType="password" label="Password" setValue={setPassword}></Input>
            <Input inputType="text" label="Name" setValue={setName}></Input>
            <Input inputType="text" label="Last Name" setValue={setLastName}></Input>
            <Input inputType="text" label="Phone Number" setValue={setPhone}></Input>
            <Input inputType="text" label="Address" setValue={setAddress}></Input>
            <Button text="Register" type="submit" onClickHandler={onRegisterClick}/>
        </RegisterPageStyle>
    )

}

export default RegisterPage;