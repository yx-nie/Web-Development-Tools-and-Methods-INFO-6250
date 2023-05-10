import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../Auth/AuthContext';
import axios from 'axios';
import { useState } from 'react';


function AddTicket(){
    let navigate=useNavigate();
    const [isLoggedIn, setIsLoggedIn]=useContext(AuthContext)
    const token=sessionStorage.getItem('token');
    const username=sessionStorage.getItem('user');

    const [ticket, setTicket]=useState({
        flightid:'',
        departure:'',
        arrival:'',
        date:'',
        operationAirline:username,
        price:'',
        stock:'',
    });

    const onInputChange=(e)=>{
        setTicket({
            ...ticket,
            [e.target.name]:e.target.value
        })
    }

    const onSubmit= async(e)=>{
        try{
        e.preventDefault();
        const response=await axios.post(`http://localhost:8084/tickets/${username}`,{
            flightid: ticket.flightid,
            departure: ticket.departure,
            arrival: ticket.arrival,
            date: ticket.date + "T00:00:00",
            operationAirline: ticket.operationAirline,
            price: ticket.price,
            stock: ticket.stock,
            sale: ticket.sale
        },{
            headers:{
                Authorization: `Bearer ${token}`,
            },
        });

        if((await response).status===200){
            navigate('/TicketManagement');
        }else{
            alert("Failed to add ticket")
        }
    }catch(err){
        alert("Failed to add ticket")
    }
}



    return (
        <div className="wrap">
          <div className="ticketContainer">
            <form onSubmit={(e) => onSubmit(e)}>
              <table className="table border shadow">
                <thead>
                  <tr>
                    <th scope="col">Flight</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                    <th scope="col">Date</th>
                    <th scope="col">Operation Airline</th>
                    <th scope="col">Price</th>
                    <th scope="col">Stock</th>
                    {/* <th scope="col">Sale</th> */}
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <input
                        className="form-control"
                        id="flightid"
                        name="flightid"
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    <td>
                      <input
                        className="form-control"
                        id="departure"
                        name="departure"
                        required
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    <td>
                      <input
                        className="form-control"
                        id="arrival"
                        name="arrival"
                        required
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    <td>
                      <input
                        type="date"
                        className="form-control"
                        id="date"
                        name="date"
                        required
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    <td>
                      <input
                        className="form-control"
                        id="operationAirline"
                        name="operationAirline"
                        value={username}
                       
                      />
                    </td>
                    <td>
                      <input
                        className="form-control"
                        id="price"
                        name="price"
                        required
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    <td>
                      <input
                        className="form-control"
                        id="stock"
                        name="stock"
                        required
                        onChange={(e) => onInputChange(e)}
                      />
                    </td>
                    {/* <td>
                      <input
                        className="form-control"
                        type="checkbox"
                        id="sale"
                        name="sale"
                     
                        onChange={(e) => onInputChange(e)}
                      />
                    </td> */}
                  </tr>
                </tbody>
              </table>
              <div className="form-group">
                <input type="submit" value="Submit" className="btn btn-primary" />
              </div>
            </form>
          </div>
        </div>
      );      
}

export default AddTicket