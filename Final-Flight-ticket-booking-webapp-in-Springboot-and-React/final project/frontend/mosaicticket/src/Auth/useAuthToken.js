import { useEffect} from "react";
import { useNavigate } from "react-router-dom";


function TokenExpire(){
    const tokenExpireDate=sessionStorage.getItem('tokenExpireDate');
    return Date.now()>tokenExpireDate; 
   
}

export function useAuthToken(){
    const navigate=useNavigate();

    useEffect(()=>{
        if(TokenExpire){
            sessionStorage.removeItem('token');
            navigate('/Login');
        }
    },[navigate]);

}

