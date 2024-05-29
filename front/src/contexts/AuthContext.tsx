import { ReactNode, createContext, useContext, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthResponse } from "../models/users/AuthResponse";
import { addTokenToRequestInterceptor, ejectInterceptor } from "../interceptors/RequestInterceptor";


interface AuthContextType {
    user: {username: string} | null;
    token: String | null;
    onLogin: (userData: any) => void;
    onLogout: () => void;
    isAuthenticated: () => boolean;
  }

const AuthContext = createContext<AuthContextType>({
    user: null,
    token: null,
    onLogin: () => {},
    onLogout: () => {},
    isAuthenticated: () => false
});

export const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<{username: string} | null>(null);
    const [token, setToken] = useState<string|null>(null);
    const [interceptorId, setInterceptorId] = useState<number>(-1)
    const navigate = useNavigate();

    const onLogin = (userData: AuthResponse) => {
        console.log("Hey from on login, setting token and username" , userData)
        setToken(userData.accessToken);
        let user = {username: userData.username}
        setUser(user)
        let interceptor = addTokenToRequestInterceptor(userData.accessToken);
        setInterceptorId(interceptor);
        navigate('/')
    };
  
    const onLogout = () => {
      setToken(null);
      setUser(null)
      ejectInterceptor(interceptorId);
      navigate('/');
    };

    const isAuthenticated = (): boolean => {
      console.log(token === null)
      return token === null;
    }

    const contextValue = useMemo(
        () => ({
          user,
          token,
          onLogin,
          onLogout,
          isAuthenticated
        }),
        [user, token]
      );
  
    return (
      <AuthContext.Provider value={contextValue}>
        {children}
      </AuthContext.Provider>
    );
  };
  
  export const useAuth = () => useContext(AuthContext);