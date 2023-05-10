import axios from 'axios';
import { useContext, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom'
import { useState } from "react"

import TicketModal from './TicketModal';
import { AuthContext } from '../Auth/AuthContext';
import { TicketContext } from '../Auth/TicketContext';

function TicketInfo(){
    let navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)
    const token=sessionStorage.getItem('token');

    const{date, price, operationAirline}=useParams();


    const [ticketSearch, setTicketSearch]=useState({
        departure:'',
        arrival:'',
        date:'',
    });

    const onInputChange=(e)=>{
        setTicketSearch({...ticketSearch, [e.target.name]: e.target.value});
    }

    const onSubmit=(e)=>{
        e.preventDefault();
        loadTickets();
    }
    


    const loadTickets= async()=>{

        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

        const departureEncoded = encodeURIComponent(ticketSearch.departure);
        const arrivalEncoded = encodeURIComponent(ticketSearch.arrival);
        const dateWithTime = ticketSearch.date + "T00:00:00";
        const dateEncoded = encodeURIComponent(dateWithTime);

        const requestedUrl=`http://localhost:8080/tickets/${departureEncoded}/${arrivalEncoded}/${dateEncoded}`;
        console.log(ticketSearch.departure);
        console.log(ticketSearch.arrival);
        console.log(ticketSearch.date);
        console.log(token)
   

        const response= await axios.get(requestedUrl,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        const flattendTickets=response.data.flat();

        setTicket(flattendTickets);
    }

    const [tickets, setTicket]=useState([
    ])

    const [isModalOpen, setIsModalOpen]=useState(false);

    function openModal(){
        setIsModalOpen(true);
    }

    function closeModal(){
        setIsModalOpen(false);
    }

    const [selectedTicket, setSelectedTicket]=useContext(TicketContext);



    function handleSelectedTicket(ticket){
        setSelectedTicket(ticket);
      

        openModal();
    }

    

    return(
        <div className="wrap">
            <div className="ticketContainer">
                <div className="row">
                    <form onSubmit={(e)=>onSubmit(e)}>
                	    <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input className= 'form-control' id="departure" name="departure" placeholder='🛫 Start from' onChange={(e)=>onInputChange(e)}/>
                                </div>
                            </h3>
                        </div>
                	    <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input className= 'form-control' id="arrival" name="arrival" placeholder='🛬 where to go' onChange={(e)=>onInputChange(e)}/>
                                </div>
                            </h3>
                        </div>
                        <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input type="date" className= 'form-control' id="date" name="date" placeholder="check in/out date" onChange={(e)=>onInputChange(e)}/>
                                </div>
                            </h3>
                        </div>
                        <div className='col-sm-1'>
                            <h3>
                                <div className='form-group'>
                                    <input type="submit" value="Search again" className="btn btn-primary btn-sm"/>
                                </div>
                            </h3>
                        </div>
                    </form>
                </div>
                <div className="row">
                	<div className="row">
                        <div className="seeking">
                        {/* <form>
                            <div className='row'>
                                <div className='col-sm-4'>
                                    <p className="monospace">
                                        <i className="fas fa-moon"></i>Filters
                                    </p>
                                </div>
                                <div className="col-sm-4">
                                    <div className="form-check">
                                        <input className="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1"/>
                                        <label className="form-check-label" for="flexRadioDefault1">
                                          Cheapest
                                        </label>
                                    </div>
                                </div>
                                <div className="col-sm-4">
                                    <div className="form-check">
                                        <input className="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1"/>
                                        <label className="form-check-label" for="flexRadioDefault1">
                                          Fastest
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </form> */}
                	    
                        <table className="table border shadow">
                            <thead>
                              <tr>
                                <th scope='col'>id</th>
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
                                { tickets.map(ticket =>(
                                    <tr>
                                        <td>{ticket.id}</td>
                                        <td>{ticket.flightid}</td>
                                        <td>{ticket.departure}</td>
                                        <td>{ticket.arrival}</td>
                                        <td>{ticket.date}</td>
                                        <td>{ticket.operationAirline}</td>
                                        <td>{ticket.price}</td>
                                        <td>
                                            <button className='btn btn-primary mx-2' onClick={()=>handleSelectedTicket(ticket)}>View</button>
                                        </td>
                                    </tr>
                                    ))
                                    }
                            </tbody>
                        </table>
                        <TicketModal ticket={selectedTicket} isModalOpen={isModalOpen} closeModal={closeModal} />
                        </div>


                	</div>
                </div>
                <section>
                	<h2 className="monospace">Upcoming top selections</h2>
                	<div className="row">
                
                		<div className="col-sm-4">
                			<div className="tile tile-show">
                            <img className="tickets" src={process.env.PUBLIC_URL + "/static/img/green2.png"} alt="tickets" />
                				<h5><a href="/venues/{{ show.venue_id }}">Tickets</a></h5>
                				<h6>date</h6>
                			</div>
                		</div>
                
                	</div>
                </section>
        </div>
    </div>
    )

}

export default TicketInfo