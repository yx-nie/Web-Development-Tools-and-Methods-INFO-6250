import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { useEffect,useState } from 'react';
import Modal from 'react-modal';
import axios from 'axios';
import { useNavigate, useParams } from "react-router-dom";  
import { AuthContext } from "../Auth/AuthContext";
function OrderTicket({isModalOpen,
    closeModal, ticket}){
        const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)

        let navigate=useNavigate();
        const token=sessionStorage.getItem('token');
        const username=sessionStorage.getItem('user');

        const [order, setOrder]=useState({
            flightid:ticket.flightid,
            departure:ticket.departure,
            arrival:ticket.arrival,
            date:ticket.date,
            operationAirline:username,
            price:ticket.price,
            sale:'',
            stock:'',  
        })

        useEffect(() => {
            setOrder((prevOrder) => ({
              ...prevOrder,
              flightid: ticket.flightid,
              departure: ticket.departure,
              arrival: ticket.arrival,
              date: ticket.date,
              price: ticket.price,
            }));
          }, [ticket]);

        const onInputChange=(e)=>{
            setOrder({
                ...order,
                [e.target.name]:e.target.value
            })
            
        }

        const OrderTicket=async()=>{
            if(!isLoggedIn){
                navigate('/Login');
                return;
            }



            if (parseInt(order.stock) <= parseInt(ticket.stock)){
            console.log(order.stock+"OrderTicket*****check");
            console.log(order)
            const response=await axios.put(`http://localhost:8084/tickets/${ticket.operationAirline}/${ticket.id}/${order.stock}`,{
                headers:{
                    Authorization: `Bearer ${token}`,
                }
            });

            if(response.status===200){
                alert("Order Successfully");
                
                const userTicketResponse=await axios.post(`http://localhost:8084/tickets/${username}`,order, {
                    headers:{
                        Authorization: `Bearer ${token}`,
                    }
                });

                if((await userTicketResponse).status===200){
                    navigate("/TicketManagement");
                }else{
                    alert("Error");
                }
            }
            
        }
    }

    
    return(
<div>
<Modal isOpen={isModalOpen} onRequestClose={closeModal}>
      <div className="row">
                	<div className="row">
                        <div className="seeking">
                        <table className="table border shadow">
                            <thead>
                              <tr>
                                <th scope='col'>id</th>
                                <th scope="col">flightid</th>   
                                <th scope="col">departure</th>
                                <th scope="col">arrival</th>
                                <th scope="col">date</th>
                                <th scope="col">operationAirline</th>
                                <th scope="col">price</th>
                                <th scope="col">stock</th>
                                <th scope="col">Order Quantity</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                    <tr>
                                        <td>{ticket.id}</td>
                                        <td>{ticket.flightid}</td>
                                        <td>{ticket.departure}</td>
                                        <td>{ticket.arrival}</td>
                                        <td>{ticket.date}</td>
                                        <td>{ticket.operationAirline}</td>
                                        <td>{ticket.price}</td>
                                        <td>{ticket.stock}</td>
                                        <td>
                                            <input type="text" id="stock" name="stock" onChange={(e)=>onInputChange(e)} />
                                        </td>
                       
                                        <td>
                                        <button className='btn btn-primary mx-2' onClick={()=>OrderTicket()}>Submit Order
                                        </button>
                                        </td>
                                    </tr>
                            </tbody>
                        </table>
                        </div>


                	</div>
                </div>
        <button className='btn btn-primary mx-2' onClick={closeModal}>Close</button>
      </Modal>
    </div>
    )
}

export default OrderTicket