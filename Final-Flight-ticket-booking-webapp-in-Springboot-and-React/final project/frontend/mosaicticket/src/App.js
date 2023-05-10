import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './Layout/Navbar';
import Guest from './Guest/Guest';
import Footer from './Layout/Footer';
import Registration from './regi/Registration';
import Login from './login/Login';
import Logout from './logout/Logout';
import Home from './home/Home';
import TicketInfo from './home/TicketInfo';
import { AuthProvider } from './Auth/AuthContext';
import Profile from './Profile/Profile';
import Orders from './home/Orders';
import TicketModal from './home/TicketModal';
import OrderTicket from './home/OrderTicket';
import { TicketProvider } from './Auth/TicketContext';
import Admin from './Admin/Admin';
import OrdersAdmin from './Admin/OrdersAdmin';

function App() {
  return (
    <div className="App">
      <AuthProvider>
      <TicketProvider>
      <Router>
         <Navbar/>
         
         {/* <Guest /> */}
         <Routes>
          <Route eaxct path='/Registration' element={<Registration />} />
          <Route exact path='/Login' element={<Login />} />
          <Route exact path='/Logout' element={<Logout />} />
          <Route exact path='/Home' element={<Home />} />
          <Route exact path='/TicketInfo' element={<TicketInfo />} />
          <Route exact path='/Profile' element={<Profile />} />
          <Route exact path='/Orders' element={<Orders />} />
          <Route exact path='/TicketModal' element={<TicketModal />} />
          <Route exact path='/OrderTicket' element={<OrderTicket />} />
          <Route exact path='/Admin' element={<Admin />} />
          <Route exact path='/OrdersAdmin' element={<OrdersAdmin />} />
     
         </Routes>
         <Footer />
      </Router>
      </TicketProvider>
      </AuthProvider>
    </div>
  );
}

export default App;
