import axios from 'axios';
import { useEffect, useState } from 'react';

function Orders(){
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const [orders, setOrders]=useState([]);

    useEffect(()=>{
        loadOrders();
    },[])

    const loadOrders=async()=>{
        const response=await axios.get(`http://localhost:8080/orders/${username}`,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });
        setOrders(response.data);

    }


    return(
        <div className="wrap">
            <div className="ordersContainer">
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
                              
                              </tr>
                            </thead>
                            <tbody>
                                { orders.map(order =>(
                                    <tr>
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
                                        
                                    </tr>
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

export default Orders