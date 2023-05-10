import React, { useEffect } from 'react';
import { useState } from 'react';


export const AuthContext=React.createContext();

export const AuthProvider=({children})=>{
    const [isLoggedIn, setIsLoggedIn]=useState(false);

    useEffect(()=>{
        const token=sessionStorage.getItem('token');
        const tokenExpireDate=sessionStorage.getItem('tokenExpireDate');
        if(token && tokenExpireDate && Date.now()<Number(tokenExpireDate)){
            setIsLoggedIn(true);
        }else{
            setIsLoggedIn(false);
        }

    },[]);


    return(
        <AuthContext.Provider value={[isLoggedIn, setIsLoggedIn]}>
            {children}
        </AuthContext.Provider>
    );

};

