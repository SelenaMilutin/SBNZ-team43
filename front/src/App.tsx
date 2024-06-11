import { BrowserRouter, Routes, Route } from "react-router-dom";

import './App.css';
import LoginPage from "./pages/login/LoginPage";
import NavBar from "./components/shared/NavBar/NavBar";
import RegisterPage from "./pages/register/RegisterPage";
import { AuthProvider } from "./contexts/AuthContext";
import ProfilePage from "./pages/profile/ProfilePage";
import LandingPage from "./pages/landing/LandingPage";
import ServiceAreaPage from "./pages/servicearea/ServiceAreasPage";
import CreateContractPage from "./pages/contract/CreateContractPage";
import TechnicalIssuesPage from "./pages/technicalissues/TechnicalIssuesPage";
import ComplaintPage from "./pages/complaint/ComplaintPage";
import ReportPage from "./pages/report/ReportPage";

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
                <Route path="contract/create" element={<CreateContractPage/>}/>
                <Route path="technicalissue" element={<TechnicalIssuesPage/>}/>
                <Route path="complaint" element={<ComplaintPage/>}/>
                <Route path="report" element={<ReportPage/>}/>
              </Route>
          </Routes>
        </AuthProvider>
      </BrowserRouter>
  );
}

export default App;
