import { Link, Outlet } from "react-router-dom"
import Button from "../Button/Button";
import { Footer, LinkStyle, NavBarStyle } from "./NavBar.style";
import { useAuth } from "../../../contexts/AuthContext";


export default function NavBar() {

    const { token, onLogout } = useAuth();

    return (
        <>
            <NavBarStyle>
                <div>
                    {/* <img  className='logo' src={logo} alt="logo"/> */}
                    <LinkStyle to="/movie-repertoire">Home</LinkStyle>
                </div>
                <LinkStyle to="/abc">abc</LinkStyle>
                { token === null && <LinkStyle to="/login">Login</LinkStyle>}
                { token === null && <LinkStyle to="/register">Register</LinkStyle>}
                { !(token === null) && <Button text={"Logout"} type={"button"} onClickHandler={onLogout} />}
            </NavBarStyle>
            <Outlet/>
            <Footer><p>Copyright</p></Footer>
        </>
    )
}