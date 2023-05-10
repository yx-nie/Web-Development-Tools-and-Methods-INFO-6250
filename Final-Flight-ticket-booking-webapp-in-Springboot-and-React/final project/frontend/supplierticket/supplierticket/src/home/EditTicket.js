import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import Modal from 'react-modal';
import { Button } from 'bootstrap';
import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';
import { AuthContext } from "../Auth/AuthContext";
import { useNavigate, useParams } from "react-router-dom";


function EditTicket({isModalOpen,
    closeModal, ticket}){
    
        let navigate=useNavigate();
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)


    const [editTicket , setEditTicket]=useState({
        id:ticket.id,
        flightid:ticket.flightid,
        departure:ticket.departure,
        arrival:ticket.arrival,
        date:ticket.date,
        operationAirline:ticket.operationAirline,
        price:ticket.price,
        stock:ticket.stock,
        sale:ticket.sale,
    })

    useEffect(() => {
        setEditTicket({
          id: ticket.id,
          flightid: ticket.flightid,
          departure: ticket.departure,
          arrival: ticket.arrival,
          date: ticket.date,
          operationAirline: ticket.operationAirline,
          price: ticket.price,
          stock: ticket.stock,
          sale: ticket.sale,
        });
      }, [ticket]);

    const handleInputChange=(e, field)=>{
        if (field === 'sale') {
            setEditTicket({
              ...editTicket,
              sale: e.target.checked,
            });
          } else {
            setEditTicket({
              ...editTicket,
              [field]: e.target.value,
            });
          }
    }

    const updateTicket=async()=>{

        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

        try{
        console.log(editTicket.sale+"updateTicket*****check");
        const response=await axios.put(`http://localhost:8084/tickets/${username}/${ticket.id}`,editTicket,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        if((await response).status===200){
            closeModal();
        }else{
            alert("Failed to update")
        }
        
    }catch(err){
        alert("Failed to update")
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
                                <th scope="col">sale</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                    <tr>
                                    <td>
                      <input
                        type="text"
                        value={editTicket.id}
                        onChange={(e) => handleInputChange(e, 'id')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.flightid}
                        onChange={(e) => handleInputChange(e, 'flightid')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.departure}
                        onChange={(e) => handleInputChange(e, 'departure')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.arrival}
                        onChange={(e) => handleInputChange(e, 'arrival')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.date}
                        onChange={(e) => handleInputChange(e, 'date')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.operationAirline}
                        onChange={(e) => handleInputChange(e, 'operationAirline')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.price}
                        onChange={(e) => handleInputChange(e, 'price')}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        value={editTicket.stock}
                        onChange={(e) => handleInputChange(e, 'stock')}
                      />
                    </td>
                    <td>
                      <input
                          type="checkbox"
                          checked={editTicket.sale}
                          onChange={(e) => handleInputChange(e, 'sale')}
                      />
                    </td>
                                        <td>
                                        <button className='btn btn-primary mx-2' onClick={()=>updateTicket()}>save
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

export default EditTicket