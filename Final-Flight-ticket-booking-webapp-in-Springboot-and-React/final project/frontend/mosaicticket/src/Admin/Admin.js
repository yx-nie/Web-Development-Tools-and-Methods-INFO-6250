import React, { useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../Auth/AuthContext';
import { useState } from 'react';
import axios from 'axios';
function Admin(){
    const navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const role=sessionStorage.getItem('role');

    const [users, setUsers]=useState([]);

    useEffect(()=>{
        loadUsers();
    },[]);
    
    const loadUsers=async()=>{
        
        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

        const response=await axios.get('http://localhost:8080/users',{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        
        setUsers(response.data);
        
    }

    
    const deleteUser=async(id)=>{
        try{
            const response=await axios.delete(`http://localhost:8080/users/${id}`,{
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            });
            if(response.status===200){
                setUsers(users.filter(user=>user.id!==id));
            }
        }

        catch(error){
            console.log(error);
        }
        
    }




    return (
<div className="wrap">
            <div className="ticketContainer">
                <div className="row">
                </div>
                <div className="row">
                	<div className="row">
                        <div className="seeking">
                	    
                        <table className="table border shadow">
                            <thead>
                              <tr>
                                <th scope='col'>id</th>
                                <th scope="col">username</th>
                                <th scope="col">email</th>
                                <th scope="col">phone</th>
                                <th scope="col">role</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                { users.map(user =>(
                                    user && (<tr>
                                        <td>{user.id}</td>
                                        <td>{user.username}</td>
                                        <td>{user.email}</td>
                                        <td>{user.phone}</td>
                                        <td>{user.role}</td>
                                        <td>
                                        {user.role!=='admin' && <button className='btn btn-danger mx-2' onClick={()=> deleteUser(user.id)}>Delete</button>}
                                        </td>
                                    </tr>)
                                    ))
                                    }
                            </tbody>
                        </table>

                        </div>
                	</div>
                </div>
        </div>
    </div>
    )
}

export default Admin