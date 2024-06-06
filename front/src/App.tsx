import { BrowserRouter, Routes, Route } from "react-router-dom";

import './App.css';
import LoginPage from "./pages/login/LoginPage";
import NavBar from "./components/shared/NavBar/NavBar";
import RegisterPage from "./pages/register/RegisterPage";
import { AuthProvider } from "./contexts/AuthContext";
import ProfilePage from "./pages/profile/ProfilePage";
import LandingPage from "./pages/landing/LandingPage";
import ServiceAreaPage from "./pages/servicearea/ServiceAreasPage";

function App() {
  return (
    <BrowserRouter>
        <AuthProvider>
          <Routes>
              <Route path="/" element={<NavBar/>}> 
                <Route path="home" element={<LandingPage/>}/>
                <Route path="login" element={<LoginPage/>}/>
                <Route path="register" element={<RegisterPage/>}/>
                <Route path="profile" element={<ProfilePage/>}/>
                <Route path="servicearea" element={<ServiceAreaPage/>}/>
              </Route>
          </Routes>
        </AuthProvider>
      </BrowserRouter>
  );
}

export default App;
