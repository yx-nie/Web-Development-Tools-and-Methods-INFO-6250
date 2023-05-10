import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../Auth/AuthContext";

function Logout(){
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);

    let navigate =useNavigate();
    useEffect(()=>{
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('role');
        setIsLoggedIn(false);
        navigate('/Home');
    })

    return null;

}

export default Logout