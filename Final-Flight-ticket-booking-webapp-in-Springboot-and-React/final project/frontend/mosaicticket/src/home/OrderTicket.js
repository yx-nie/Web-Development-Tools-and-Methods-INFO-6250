import { useEffect, useState } from "react";
import { useContext } from "react";
import { AuthContext } from "../Auth/AuthContext";
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';
import { TicketContext } from '../Auth/TicketContext';

function OrderTicket(){

    let navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext);
    const [selectedTicket, setSelectedTicket]=useContext(TicketContext)
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');


    if(!isLoggedIn){
        navigate('/Login');
    }

    const [order, setOrder]=useState({
        firstname:'',
        lastname:'',
        email:'',
        phone:'',
        passportNo:'',
        birthday:'',
        address:'',
        city:'',
        ticketid:selectedTicket.id,
        flightid:selectedTicket.flightid,
        departure:selectedTicket.departure,
        arrival:selectedTicket.arrival,
        date:selectedTicket.date,
        operationAirline:selectedTicket.operationAirline,
        prices:selectedTicket.price,
    })


    const onInputChange= (e)=>{
        setOrder({...order, [e.target.name]: e.target.value})
    }

    const onSubmit= async(e)=>{
        try{
        console.log(order);
        e.preventDefault();
        const response=await axios.post(`http://localhost:8080/orders/${username}/${order.ticketid}/${order.flightid}`,order,{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        if(response.status===200){
            navigate('/Orders');
        }else{
            alert('Failed to submit the order. Please try again.')
        }

       
    }catch(err){
        alert('Failed to submit the order. Please check your network connection and try again.')
    }
}



    return(
<div className="wrap"> 
        <div className="orderticketcontainer">
            <div className="row">
                <div className="col-sm-6"> 
                    <div className="form-wrapper">
                        <form>
                            <h3 className="form-heading">Your Ticket Info</h3>
                            <div className="form-group">
                                <label for="ticketid" className="form-label">Ticket Id </label>
                                <input className= 'form-control' id="ticketid" name="ticketid" value={selectedTicket.id}/>
                            </div>

                            <div className="form-group">
                                <label for="flightid" className="form-label">Flight Id </label>
                                <input className= 'form-control' id="flightid" name="flightid" value={selectedTicket.flightid}/>
                            </div>
                        
                              <div className="form-group">
                                <label for="departure" className="form-label">departure </label>
                                <input className= 'form-control' id="departure" name="departure" value={selectedTicket.departure}/>
                              </div>
                        
                            <div className="form-group">
                                  <label for="arrival" className="form-label">arrival </label>
                                  <input className= 'form-control' id="arrival" name="arrival" value={selectedTicket.arrival}/>
                            </div>
                        
                            <div className="form-group">
                                <label for="date" className="form-label">Date </label>
                                <input className= 'form-control' id="date" name="date" value={selectedTicket.date}/>
                            </div>

                            <div className="form-group">
                                <label for="operationAirline" className="form-label">operationAirline </label>
                                <input className= 'form-control' id="operationAirline" name="operationAirline" value={selectedTicket.operationAirline}/>
                            </div>

                            <div className="form-group">
                                <label for="price" className="form-label">price </label>
                                <input className= 'form-control' id="price" name="price" value={selectedTicket.price}/>
                            </div>



                        </form>
                    </div>
                </div>
   
                <div className="col-sm-6">
                    <div className="form-wrapper">
                        <form onSubmit={(e)=>onSubmit(e)}>
                            <h3 className="form-heading">Order Ticket</h3>
                            <p>Please make sure your info are correctly filled!</p>
                                <div className="form-group">
                                    <label for="Firstname" className="form-label">First Name </label>
                                    <input className= 'form-control' id="firstname" name="firstname" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="lastname" className="form-label">Last Name </label>
                                    <input className= 'form-control' id="lastname" name="lastname" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="email" className="form-label">Email </label>
                                    <input className= 'form-control' id="email" name="email" pattern="(?=.*[.@]" required onChange={(e)=>onInputChange(e)}/>
                                  </div>
                                  <div className="form-group">
                                      <label for="phone" className="form-label">Phone </label>
                                      <input className= 'form-control' placeholder='xxx-xx-xxx' id="phone" name="phone" required pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="passportNo" className="form-label">PassportNo </label>
                                    <input className= 'form-control' id="passportNo" name="passportNo" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="birthday" className="form-label">Birthday </label>
                                    <input className= 'form-control' id="birthday" name="birthday" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="address" className="form-label">Address </label>
                                    <input className= 'form-control' id="address" name="address" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <div className="form-group">
                                    <label for="city" className="form-label">City </label>
                                    <input className= 'form-control' id="city" name="city" required onChange={(e)=>onInputChange(e)}/>
                                </div>
                                <input type="submit" value="Confirm Order" class="btn btn-primary btn-lg"/>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
        

    )

}

export default OrderTicket