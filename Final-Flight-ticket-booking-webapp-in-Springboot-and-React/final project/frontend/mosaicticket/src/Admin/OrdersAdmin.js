import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../Auth/AuthContext';
import { useContext, useEffect, useState } from 'react';


function OrdersAdmin(){

    const navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const role=sessionStorage.getItem('role');

    const [orders, setOrders]=useState([]);

    useEffect(()=>{
        loadOrders();
    },[]);
    
    const loadOrders=async()=>{
        
        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

        const response=await axios.get('http://localhost:8080/orders',{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        
        setOrders(response.data);
        
    }

    
    const deleteOrder=async(id)=>{
        try{
            const response=await axios.delete(`http://localhost:8080/orders/${id}`,{
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            });
            if(response.status===200){
                setOrders(orders.filter(order=>order.id!==id));
            }
        }

        catch(error){
            console.log(error);
        }
        
    }


    return(
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
                              <th scope='col'>firstname</th>
                                <th scope="col">lastname</th>
                                <th scope="col">email</th>
                                <th scope="col">phone</th>
                                <th scope="col">passportNo</th>
                                <th scope="col">birthday</th>
                                <th scope="col">address</th>
                                <th scope="col">city</th>
                                <th scope="col">ticketid</th>
                                <th scope="col">flightid</th>
                                <th scope="col">departure</th>
                                <th scope="col">arrival</th>
                                <th scope="col">date</th>
                                <th scope="col">operationAirline</th>
                                <th scope="col">prices</th>
                                <th scope="col">username</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                { orders.map(order =>(
                                    order && (<tr>
                                        <td>{order.firstname}</td>
                                        <td>{order.lastname}</td>
                                        <td>{order.email}</td>
                                        <td>{order.phone}</td>
                                        <td>{order.passportNo}</td>
                                        <td>{order.birthday}</td>
                                        <td>{order.address}</td>
                                        <td>{order.city}</td>
                                        <td>{order.ticketid}</td>
                                        <td>{order.flightid}</td>
                                        <td>{order.departure}</td>
                                        <td>{order.arrival}</td>
                                        <td>{order.date}</td>
                                        <td>{order.operationAirline}</td>
                                        <td>{order.prices}</td>
                                        <td>{order.username}</td>
                                        <td>
                                        {role=='admin' && <button className='btn btn-danger mx-2' onClick={()=> deleteOrder(order.id)}>Delete</button>}
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

export default OrdersAdmin