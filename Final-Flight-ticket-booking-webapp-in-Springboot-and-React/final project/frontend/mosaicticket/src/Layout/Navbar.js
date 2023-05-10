import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { useState, useEffect } from 'react';
import { AuthContext } from '../Auth/AuthContext';

export default function (){

const token=sessionStorage.getItem('token');
const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
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
                        <Link className="navbar-brand" href="/Home">🔥 Mosaic Ticket</Link>
                    </div>
                    <div className="collapse navbar-collapse">
                        <ul className="nav navbar-nav">
                            <li>
                              <Link className="nav-link active" to="/Home">Home </Link>
                            </li>
                          <li>
                            <Link className="nav-link active" to="/TicketInfo">Search Ticket</Link>
                          </li>
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                          <li>
                            {!isLoggedIn && <Link className="nav-link active" to="/Registration">Register</Link>}
                          </li>
                          <li>
                            {!isLoggedIn && <Link className="nav-link active" to="/Login">Login</Link>}
                          </li>
                          <li>
                            {isLoggedIn && role=='user' &&<Link className="nav-link active" to="/Orders">Orders</Link>}
                          </li>
                          <li>
                            {isLoggedIn && <Link className="nav-link active" to="/Profile">profile</Link>}
                          </li>
                          <li>
                            {isLoggedIn && role=='admin' && <Link className="nav-link active" to="/OrdersAdmin">Orders</Link>}
                          </li>
                          <li>
                            {isLoggedIn && role=='admin' && <Link className="nav-link active" to="/Admin">Users</Link>}
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
