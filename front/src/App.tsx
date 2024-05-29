import { BrowserRouter, Routes, Route } from "react-router-dom";

import './App.css';
import LoginPage from "./pages/login/LoginPage";
import NavBar from "./components/shared/NavBar/NavBar";
import RegisterPage from "./pages/register/RegisterPage";
import { AuthProvider } from "./contexts/AuthContext";

function App() {
  return (
    <BrowserRouter>
        <AuthProvider>
          <Routes>
              <Route path="/" element={<NavBar/>}> 
                <Route path="login" element={<LoginPage/>}/>
                <Route path="register" element={<RegisterPage/>}/>
              </Route>
          </Routes>
        </AuthProvider>
      </BrowserRouter>
  );
}

export default App;
