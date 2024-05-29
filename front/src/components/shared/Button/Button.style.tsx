import styled from "styled-components";

const ButtonStyle = styled.button`
    background-color: rgb(221, 217, 212);
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
    width: 110px;
    height: 40px;
    border: 0;
    border-radius: 7px;
    margin: 10px;
    transition: background-color 0.5s ease;
    cursor: pointer;
    &:hover {
        background-color: rgb(240, 198, 147);
    }
`

export default ButtonStyle;