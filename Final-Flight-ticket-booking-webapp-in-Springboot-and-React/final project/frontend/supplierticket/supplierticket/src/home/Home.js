import axios from 'axios';
import { useContext, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom'
import { useState } from "react"
import OrderTicket from './OrderTicket';
import { AuthContext } from '../Auth/AuthContext';



function Home(){
    let navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');
    const role=sessionStorage.getItem('role');



    const{date, price, operationAirline}=useParams();

    const [search, setSearch] = useState({
        departure: '',
        arrival: ''
    });


    const [orderTicket , setOrderTicket]=useState({
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

    

        const requestedUrl=`http://localhost:8084/tickets`;
   
        const response= await axios.get(requestedUrl,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        const flattendTickets=response.data.flat();

        setTickets(flattendTickets);
    }


    const handleSearch = (e) => {
        setSearch({...search, [e.target.name]: e.target.value});
    }

    const filterTickets = () => {
        return tickets.filter(ticket => {
            return ticket.departure.includes(search.departure) && ticket.arrival.includes(search.arrival);
        });
    }



    const [isModalOpen, setIsModalOpen]=useState(false);

    function openModal(){
        setIsModalOpen(true);
        console.log(isModalOpen);
    }

    function closeModal(){
        setIsModalOpen(false);
    }

    const onOrderClick=(ticket)=>{
        setOrderTicket(ticket);
        console.log(ticket);
        
        openModal();
        
    }
    useEffect(() => {
        console.log("isModalOpen:", isModalOpen);
    }, [isModalOpen]);
    

    const deleteTicket= async(id, operationAirline)=>{
        try{
            const response=axios.delete(`http://localhost:8084/tickets/${operationAirline}/${id}`,{
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

    return(
        <div className="wrap">
            <div className="ticketContainer">
                <div className="row">
                    <form >
                	    <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input className= 'form-control' id="departure" name="departure" placeholder='🛫 Start from' onChange={handleSearch} />
                                </div>
                            </h3>
                        </div>
                	    <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input className= 'form-control' id="arrival" name="arrival" placeholder='🛬 where to go' onChange={handleSearch}/>
                                </div>
                            </h3>
                        </div>
                        {/* <div className="col-sm-3">
                            <h3>
                                <div className="form-group">
                                    <input type="date" className= 'form-control' id="date" name="date" placeholder="check in/out date" />
                                </div>
                            </h3>
                        </div>
                        <div className='col-sm-1'>
                            <h3>
                                <div className='form-group'>
                                    <input type="submit" value="Search again" className="btn btn-primary btn-sm"/>
                                </div>
                            </h3>
                        </div> */}
                    </form>
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
                                { filterTickets().map(ticket =>(
                                    <tr>
                                        <td>{ticket.id}</td>
                                        <td>{ticket.flightid}</td>
                                        <td>{ticket.departure}</td>
                                        <td>{ticket.arrival}</td>
                                        <td>{ticket.date}</td>
                                        <td>{ticket.operationAirline}</td>
                                        <td>{ticket.price}</td>
                                        <td>{ticket.stock}</td>
                                        <td>{ticket.sale}</td>
                                        <td>
                                            {ticket.sale && role=='user' && ticket.operationAirline!==username &&<button className='btn btn-outline-primary mx-2' onClick={()=>onOrderClick(ticket)}>Order</button>}
                                            {role=='admin' && <button className='btn btn-danger mx-2' onClick={()=> deleteTicket(ticket.id, ticket.operationAirline)}>Delete</button>}
                                        </td>
                                    </tr>
                                    ))
                                    }
                            </tbody>
                        </table>

                        {orderTicket && (
                <OrderTicket
                  ticket={orderTicket}
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

export default Home