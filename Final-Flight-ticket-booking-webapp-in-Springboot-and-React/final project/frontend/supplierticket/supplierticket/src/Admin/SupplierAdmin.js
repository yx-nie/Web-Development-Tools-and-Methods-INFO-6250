import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../Auth/AuthContext';
import { useContext, useEffect, useState } from 'react';

function SupplierAdmin(){
    const navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const role=sessionStorage.getItem('role');

    const [suppliers, setSuppliers]=useState([]);

    useEffect(()=>{
        loadSuppliers();
    },[]);
    
    const loadSuppliers=async()=>{
        
        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

        const response=await axios.get('http://localhost:8084/suppliers',{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        
        setSuppliers(response.data);
        
    }

    
    const deleteSupplier=async(id)=>{
        try{
            const response=await axios.delete(`http://localhost:8084/suppliers/${id}`,{
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            });
            if(response.status===200){
                setSuppliers(suppliers.filter(supplier=>supplier.id!==id));
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
                                <th scope="col">address</th>
                                <th scope="col">uniqueid</th>
                                <th scope="col">role</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                { suppliers.map(supplier=>(
                                    supplier && (<tr>
                                        <td>{supplier.id}</td>
                                        <td>{supplier.username}</td>
                                        <td>{supplier.email}</td>
                                        <td>{supplier.address}</td>
                                        <td>{supplier.uniqueid}</td>
                                        <td>{supplier.role}</td>
                                        <td>
                                        {supplier.role!=='admin' && <button className='btn btn-danger mx-2' onClick={()=> deleteSupplier(supplier.id)}>Delete</button>}
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

export default SupplierAdmin