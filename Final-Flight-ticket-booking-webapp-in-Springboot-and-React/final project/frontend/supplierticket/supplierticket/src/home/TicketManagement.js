import axios from 'axios';
import { useNavigate, useParams } from "react-router-dom";
import { AuthContext } from "../Auth/AuthContext";
import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import EditTicket from './EditTicket';


function TicketManagement(){
    let navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const [editTicket , setEditTicket]=useState({
        id:'',
        flightid:'',
        departure:'',
        arrival:'',
        date:'',
        operationAirline:'',
        price:'',
        stock:'',
        sale:'',
    })


    const[tickets, setTickets]=useState([]);

    useEffect(()=>{
        loadTickets();
    },[]);
    


    const loadTickets= async()=>{

        if(!isLoggedIn){
            navigate('/Login');
            return;
        }

   
        const response= await axios.get(`http://localhost:8084/tickets/${username}`,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        const flattendTickets=response.data.flat();

        setTickets(flattendTickets);
    }

    const deleteTicket= async(id)=>{
        try{
            const response=axios.delete(`http://localhost:8084/tickets/${username}/${id}`,{
                headers:{
                    Authorization: `Bearer ${token}`,
                },
            });

            if((await response).status===200){
                setTickets(tickets.filter(ticket=>ticket.id!==id));
            }
        }

        catch(err){
            console.log(err);
        }

    }

    const [isModalOpen, setIsModalOpen]=useState(false);

    function openModal(){
        setIsModalOpen(true);
        console.log(isModalOpen);
    }

    function closeModal(){
        setIsModalOpen(false);
    }

    const onEditClick=(ticket)=>{
        setEditTicket(ticket);
        console.log(ticket);
        
        openModal();
        
    }
    useEffect(() => {
        console.log("isModalOpen:", isModalOpen);
    }, [isModalOpen]);




    return(
<div className="wrap">
            <div className="ticketContainer">
                <div className="row">
                <Link className="btn btn-outline-light" to="/AddTicket">Add Ticket</Link>
                </div>
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
                                <th scope="col">Sale</th>
                                <th scope="col"></th>
                              </tr>
                            </thead>
                            <tbody>
                                { tickets.map(ticket =>(
                                    ticket && (<tr>
                                        <td>{ticket.id}</td>
                                        <td>{ticket.flightid}</td>
                                        <td>{ticket.departure}</td>
                                        <td>{ticket.arrival}</td>
                                        <td>{ticket.date}</td>
                                        <td>{ticket.operationAirline}</td>
                                        <td>{ticket.price}</td>
                                        <td>{ticket.stock}</td>
                                        <td>{ticket.sale ? "True" : "False"}</td>
                                        <td>
                                        <button className='btn btn-outline-primary mx-2' onClick={()=>onEditClick(ticket)}>Edit</button>
                                        <button className='btn btn-danger mx-2' onClick={()=> deleteTicket(ticket.id)}>Delete</button>
                                        </td>
                                    </tr>)
                                    ))
                                    }
                            </tbody>
                        </table>
                        {editTicket && (
                <EditTicket
                  ticket={editTicket}
                  isModalOpen={isModalOpen}
                  closeModal={closeModal}
                />
              )}

                        </div>
                	</div>
                </div>
        </div>
    </div>
    )
}

export default TicketManagement