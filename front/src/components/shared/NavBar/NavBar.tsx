import { Link, Outlet } from "react-router-dom"
import Button from "../Button/Button";
import { Footer, LinkStyle, NavBarStyle } from "./NavBar.style";
import { useAuth } from "../../../contexts/AuthContext";
// import useWebSocket from "react-use-websocket";
import { CompatClient, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { WEB_SOCKET_URL } from "../../../constants/Constants";
import { useEffect, useState } from "react";
import { MessageNotification } from "../../../models/notifications/MessageNotification";
import { formatDateTime } from "../../../utils/formats/DateTimeFormat";


export default function NavBar() {

    const { user, token, onLogout } = useAuth();
    // const {
    //     sendMessage,
    //     sendJsonMessage,
    //     lastMessage,
    //     lastJsonMessage,
    //     readyState,
    //     getWebSocket,
    //   } = useWebSocket(WEB_SOCKET_URL+"/notification/" + user?.username, {
    //     onOpen: () => console.log('opened'),
    //     shouldReconnect: (closeEvent) => true,
    //   });


    // useEffect(() => {
    //     console.log(`Got a new message: ${lastJsonMessage}`)
    //   }, [lastJsonMessage])

    const [stompClient, setStompClient] = useState<CompatClient | null>(null);

    useEffect(() => {
        console.log(user);
        if (user !== null) {
            const socket = new SockJS(WEB_SOCKET_URL);
            const client = Stomp.over(socket);
          
            client.connect({}, (frame: any) => {
              console.log('Connected: ' + frame);
              client.subscribe('/notification/' + user?.username, (message) => {
                console.log(message.body);
                let notification: MessageNotification = JSON.parse(message.body);
                alert(notification.message + " at " + formatDateTime(new Date(notification.timestamp)))
              });
            });
          
            // Update stompClient state with the new client instance
            setStompClient(() => client);
          
            // Cleanup function
            return () => {
              if (client !== null && client.connected) {
                client.disconnect();
              }
            };
        }
      }, [user]);

    return (
        <>
            <NavBarStyle>
                <LinkStyle to="/home">Home</LinkStyle>
                { token === null && <LinkStyle to="/login">Login</LinkStyle>}
                { token === null && <LinkStyle to="/register">Register</LinkStyle>}
                {/* { !(token === null) && (user?.role === "ADMIN") && <LinkStyle to="/servicearea">Service Areas</LinkStyle> } */}
                 <LinkStyle to="/servicearea">Service Areas</LinkStyle> 
                {/* { !(token === null) && (user?.role === "CLIENT") && <LinkStyle to="/contract/create">Create Contract</LinkStyle>} */}
                { <LinkStyle to="/contract/create">Create Contract</LinkStyle> }
                {/* { !(token === null) && (user?.role === "CLIENT") && <LinkStyle to="/technicalissue>Help</LinkStyle>} */}
                { <LinkStyle to="/technicalissue">Help</LinkStyle> }
                { !(token === null) && (user?.role === "CLIENT") && <LinkStyle to="/profile">Profile</LinkStyle>}
                { !(token === null) && (user?.role === "CLIENT") && <LinkStyle to="/complaint">Send Complaint</LinkStyle>}
                { !(token === null) && <Button text={"Logout"} type={"button"} onClickHandler={onLogout} />}
            </NavBarStyle>
            <Outlet/>
            <Footer><p>Copyright</p></Footer>
        </>
    )
}