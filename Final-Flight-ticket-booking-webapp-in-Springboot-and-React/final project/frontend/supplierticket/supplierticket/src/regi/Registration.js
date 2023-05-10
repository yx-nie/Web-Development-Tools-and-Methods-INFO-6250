import { htmlPrefilter } from "jquery"
import { Link, useParams, useNavigate}from 'react-router-dom'
import axios from 'axios'
import React, { useState } from 'react'



function Registration(){
    let navigate= useNavigate()

    const [user, setUser] = useState({
        username:'',
        address:'',
        uniqueid:'',
        password:'',
        email:'',
        role:'user',
    })


    const onSubmit= async(e)=>{
        e.preventDefault()
        await axios.post('http://localhost:8084/register', user)
        console.log("done")
        navigate('/login')

    }

    const onInputChange=(e)=>{
        setUser({...user, [e.target.name]: e.target.value})
    }


    return(
        <div className="wrap"> 
        
            <main id="content" role="main" className="container">
                <div className="form-wrapper">
                    <form onSubmit={(e)=>onSubmit(e)}>
                      <h3 className="form-heading">Welcome to SupplierTicket system</h3>
                      <div className="form-group">
                                <label for="username" className="form-label">Username </label>
                                <input className= 'form-control' id="username" name="username" required onChange={(e)=>onInputChange(e)}/>
                            </div>
                        
                              <div className="form-group">
                                <label for="email" className="form-label">Email </label>
                                <input className= 'form-control' id="email" name="email" pattern="(?=.*[.@]" required onChange={(e)=>onInputChange(e)}/>
                              </div>
                        
                            <div className="form-group">
                                  <label for="address" className="form-label">address </label>
                                  <input className= 'form-control' id="address" name="address" required onChange={(e)=>onInputChange(e)}/>
                            </div>

                            <div className="form-group">
                                  <label for="uniqueid" className="form-label">Unique id </label>
                                  <input className= 'form-control' id="uniqueid" name="uniqueid" required onChange={(e)=>onInputChange(e)}/>
                            </div>
                        
                            <div className="form-group">
                                <label for="password" className="form-label">Password </label>
                                <input type="password" className= 'form-control' id="password" name="password" required onChange={(e)=>onInputChange(e)}/>
                            </div>

                
                      <input type="submit" value="Register" class="btn btn-primary btn-lg"/>
                    </form>
                </div>
            </main>
            
        </div>
    )
}

export default Registration