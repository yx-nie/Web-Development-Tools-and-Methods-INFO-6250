
import React, { useContext } from 'react';
import ReactDOM from 'react-dom/client';
import { Link } from 'react-router-dom';
import { AuthContext } from '../Auth/AuthContext';
export default function NavBar() {
  const token=sessionStorage.getItem('token');
  const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)
  const role=sessionStorage.getItem('role');



    return(
<div className="wrap">
            <div className="content">
            <div className="navbar navbar-default navbar-fixed-top">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <Link className="navbar-brand" href="/Home">🎫 SupplierTicket Exchange </Link>
                    </div>
                    <div className="collapse navbar-collapse">
                        <ul className="nav navbar-nav">
                            <li>
                              <Link className="nav-link active" to="/Home">Home </Link>
                            </li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                          <li>
                            {!isLoggedIn &&<Link className="nav-link active" to="/Registration">Register</Link>}
                          </li>
                          <li>
                            {!isLoggedIn &&<Link className="nav-link active" to="/Login">Login</Link>}
                          </li>
                          <li>
                            {isLoggedIn &&<Link className="nav-link active" to="/Profile">Profile</Link>}
                          </li>
                          <li>
                            {isLoggedIn && role=='admin' &&<Link className="nav-link active" to="/SupplierAdmin">Supplier Management</Link>}
                          </li>
                          <li>
                            {isLoggedIn && role=='user' &&<Link className="nav-link active" to="/TicketManagement">Ticket Management</Link>}
                          </li>
                          <li>
                            {isLoggedIn && <Link className="nav-link active" to="/Logout">Logout</Link>}
                          </li>
                        </ul>
                    </div>
                </div>
            </div>
            </div>
        </div> 
    )
}