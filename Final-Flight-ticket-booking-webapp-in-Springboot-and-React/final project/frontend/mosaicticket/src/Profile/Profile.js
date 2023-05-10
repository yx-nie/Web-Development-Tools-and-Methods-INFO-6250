import axios from "axios";
import { useEffect } from "react";
import { useState, useHistory } from "react";
import { useAuthToken } from "../Auth/useAuthToken";


function Profile(){


    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');

    const [user, setUser]=useState({
        id:'',
        username:'',
        email:'',
        phone:'',
        password:'',
        changepassword:'',

    });

    useEffect(()=>{
        loadUser();
    },[])

    const loadUser=async(e)=>{

        const token=sessionStorage.getItem('token');
        const response=await axios.get(`http://localhost:8080/profile/${username}`,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });
        setUser(response.data);
    }

    const onInputChange=(e)=>{
        setUser({...user, [e.target.name]: e.target.value});
    }

    let updateuser={...user};
    if(user.changepassword){
        updateuser.password=user.changepassword;
    }

    const onSubmit=async (e)=>{
        try{
        e.preventDefault();
        if(user.changepassword!==""){
            user.password=user.changepassword;
        }

        const response=await axios.put(`http://localhost:8080/profile/${updateuser.id}`,updateuser,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });
        if(response.data){
            setUser(response.data);
        }else{
            alert("Update Failed");
        }
        
    }catch(err){
        alert("Update Failed");
    }
}

    const onCancel=()=>{
        loadUser();
    }


    return(
    <div className="wrap"> 
        
        <main id="content" role="main" className="container">
            <div className="form-wrapper">
                <form onSubmit={(e)=>onSubmit(e)}>
                  <h3 className="form-heading">Change My Profile</h3>
                        <div className="form-group">
                            <label for="username" className="form-label">Username </label>
                            <input className= 'form-control' id="username" name="username" value={user.username}/>
                        </div>
                    
                          <div className="form-group">
                            <label for="email" className="form-label">Email </label>
                            <input className= 'form-control' id="email" name="email" value={user.email} onChange={(e)=>onInputChange(e)}/>
                          </div>
                    
                        <div className="form-group">
                              <label for="phone" className="form-label">Phone </label>
                              <input className= 'form-control' placeholder='xxx-xxx-xxxx' id="phone" name="phone" value={user.phone} pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" onChange={(e)=>onInputChange(e)}/>
                        </div>
                    
                        <div className="form-group">
                            <label for="password" className="form-label">Password </label>
                            <input type="password" className= 'form-control' id="password" name="password" value={user.password} onChange={(e)=>onInputChange(e)}/>
                        </div>

                        <div className="form-group">
                            <label for="password" className="form-label">New Password </label>
                            <input type="password" className= 'form-control' id="changepassword" name="changepassword" onChange={(e)=>onInputChange(e)}/>
                        </div>
            
                  <input type="submit" value="Save" class="btn btn-primary btn-lg"/>
                  <input type="submit" value="Cancel" onClick={onCancel} class="btn btn-secondary btn-lg"/>
                </form>
            </div>
        </main>
        
    </div>
    )
}

export default Profile