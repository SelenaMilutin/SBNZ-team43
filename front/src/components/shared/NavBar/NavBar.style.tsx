import { Link } from "react-router-dom";
import styled from "styled-components";

const NavBarStyle = styled.div`
    background-color: rgb(225, 228, 228);
    padding: 5px 20px;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    height: 50px;
    a {
        margin: 10px;
        font-size: 18px;
        cursor: pointer;
    }
    .logo {
        margin-top: 5px;
        width: 30px;
        height: 30px;
        cursor: pointer;
    }
`

const LinkStyle = styled(Link)`
    color: black;
    text-decoration: none;
    margin: 10px;
    font-size: 20px;
    font-weight: 500;
    cursor: pointer;
`

const Footer = styled.div`
    display: flex;
    justify-content: center;
    align-items: flex-end;
    margin-top: 20vh;
    height: 20vh;
    background-color: rgb(225, 228, 228);
`

export {
    NavBarStyle,
    LinkStyle,
    Footer
}