import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import NavBar from './layout/NavBar';
import Footer from './layout/Footer';
import Login from './login/Login';
import Registration from './regi/Registration';
import { AuthProvider } from './Auth/AuthContext';
import Home from './home/Home';
import TicketManagement from './home/TicketManagement';
import AddTicket from './home/AddTicket';
import EditTicket from './home/EditTicket';
import OrderTicket from './home/OrderTicket';
import Logout from './logout/Logout';
import SupplierAdmin from './Admin/SupplierAdmin';
import Profile from './profile/Profile';



function App() {
  return (
    <div className="App">
      <AuthProvider>
      <Router >
        <NavBar />
        <Routes>
          <Route exact path="/Home" element={<Home />} />
          <Route exact path="/Login" element={<Login />} />
          <Route exact path="/Registration" element={<Registration />} />
          <Route exact path="/TicketManagement" element={<TicketManagement />} />
          <Route exact path="/AddTicket" element={<AddTicket />} />
          <Route exact path="/EditTicket" element={<EditTicket />} />
          <Route exact path="/OrderTicket" element={<OrderTicket />} />
          <Route exact path="/Logout" element={<Logout />} />
          <Route exact path="/SupplierAdmin" element={<SupplierAdmin />} />
          <Route exact path="/Profile" element={<Profile />} />

        </Routes>
        <Footer />
      </Router>
      </AuthProvider>

    </div>
  )
}

export default App;
