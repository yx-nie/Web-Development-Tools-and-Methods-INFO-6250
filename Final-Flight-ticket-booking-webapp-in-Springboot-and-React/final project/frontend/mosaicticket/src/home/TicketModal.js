import React, { useContext } from 'react';
import Modal from 'react-modal';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { TicketContext } from '../Auth/TicketContext';

function TicketModal({isModalOpen, closeModal, ticket}){
    // const [selectedTicket, setSelectedTicket]=useContext(TicketContext);
    

return(
    <div>
      <Modal isOpen={isModalOpen} onRequestClose={closeModal}>
      <div className="row">
        <p>heading Upcoming</p>
                	<div className="row">
                        <div className="seeking">
                        <table className="table border shadow">
                            <thead>
                              <tr>
                                <th scope="col">id</th>
                                <th scope="col">flightid</th>   
                                <th scope="col">departure</th>
                                <th scope="col">arrival</th>
                                <th scope="col">date</th>
                                <th scope="col">operationAirline</th>
                                <th scope="col">prices</th>
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
                                        <td>
                                        <Link className='btn btn-primary mx-2' to={`/OrderTicket`}>Order Ticket</Link>
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

export default TicketModal