import { useContext, useState } from "react"
import axios from 'axios'
import { useNavigate } from "react-router-dom"
import { AuthContext } from "../Auth/AuthContext"



function Login(){

    let navigate=useNavigate()

    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
    



    const [user, setUser]=useState({
        username:'',
        password:''
    })

    const {username, password}=user

    const onInputChange= (e)=>{
        setUser({...user, [e.target.name]: e.target.value})
    }

    const onSubmit= async(e)=>{
        e.preventDefault();
        const token=sessionStorage.getItem('token')
        const tokenExpireDate=sessionStorage.getItem('tokenExpireDate')
        console.log(token);

        if(token !=null && Date.now()<Number(tokenExpireDate)){
            console.log("second time login")
            const response= await axios.post('http://localhost:8080/auth',{},{
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            });
            setIsLoggedIn(true);
            navigate('/home');

        }else{
            try{
            console.log("first time login");
            const response=await axios.post('http://localhost:8080/login',{
                username: username,
                password: password,
            });
            console.log("**");
            console.log(username, password);

            if(response.data.token){
                sessionStorage.setItem('token', response.data.token);
                sessionStorage.setItem('user', username);
                sessionStorage.setItem('role', response.data.role);
                sessionStorage.setItem('tokenexpiredate', Date.now()+response.data.tokenExpireDate);
                console.log(response.data.token);
                console.log(response.data.role +"###############");
                setIsLoggedIn(true);
                navigate('/home');
            }else{
                throw new Error("No token in response");
            }
        }catch(err){
            if(err.response && err.response.status==401){
                alert("Invalid username or password");
            }else{
                alert("Something went wrong");
            }
        }
        }
    
    }

    return(
        <div className="wrap"> 
        
            <main id="content" role="main" className="container">
                <div className="form-wrapper">
                    <form onSubmit={(e)=>onSubmit(e)}>
                      <h3 className="form-heading">Login to enjoy more</h3>
                            <div className="form-group">
                                <label for="username" className="form-label">Username </label>
                                <input className= 'form-control'  autofocus = "true" id="username" name="username" required onChange={(e)=>onInputChange(e)}/>
                            </div>
                            <div className="form-group">
                                <label for="password" className="form-label">Password </label>
                                <input type="password" className= 'form-control'  autofocus = "true" id="password" name="password" required onChange={(e)=>onInputChange(e)}/>
                            </div>
                
                      <input type="submit" value="Login" class="btn btn-primary btn-lg"/>
                    </form>
                </div>
            </main>
            
        </div>
    )
}

export default Login