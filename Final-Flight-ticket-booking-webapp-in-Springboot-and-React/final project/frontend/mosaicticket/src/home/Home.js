import React from 'react';
import { useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthToken } from '../Auth/useAuthToken';

function Home(){

    let navigate=useNavigate();

    const [ticket, setTicket]=useState({
        departure:'',
        arrival:'',
        date:'',
    })

    const onSubmit= async(e)=>{
        navigate('/TicketInfo')
    }

    const onInputChange=(e)=>{
        
    }




    return (
        <div className="wrap">
            <div className="homecontainer">
                <div className="row">
                    <h1>Mosaic Ticket 🔥</h1>
            		<p className="lead">Find the cheapest flight for your wonderful trip.</p>
                    {/* <form onSubmit={(e)=>onSubmit(e)}>
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
                                    <input type="date" className= 'form-control' id="date" name="date" placeholder="check in/out date"/>
                                </div>
                            </h3>
                        </div>
                        <div className='col-sm-1'>
                            <h3>
                                <div className='form-group'>
                                    <input type="submit" value="Search" className="btn btn-primary btn-sm"/>
                                </div>
                            </h3>
                        </div>
                    </form> */}
                </div>
            <div className="row">
            <section>
                	<h2 className="monospace">Top places</h2>
                	<div className="row">
                		<div className="col-sm-4">
                			<div className="tile tile-show">
                            <img className="tickets" src={process.env.PUBLIC_URL + "/static/img/green3.png"} alt="tickets" />
                				<h5><a href="/venues/{{ show.venue_id }}">Tokyo</a></h5>
                				<h6>date</h6>
                			</div>
                		</div>
                        <div className="col-sm-4">
                			<div className="tile tile-show">
                            <img className="tickets" src={process.env.PUBLIC_URL + "/static/img/green2.png"} alt="tickets" />
                				<h5><a href="/venues/{{ show.venue_id }}">Toronto</a></h5>
                				<h6>date</h6>
                			</div>
                		</div>
                        <div className="col-sm-4">
                			<div className="tile tile-show">
                            <img className="tickets" src={process.env.PUBLIC_URL + "/static/img/green1.png"} alt="tickets" />
                				<h5><a href="/venues/{{ show.venue_id }}">Seattle</a></h5>
                				<h6>date</h6>
                			</div>
                		</div>
                
                	</div>
                </section>
               
            </div>
            </div>
        </div>
    )

}

export default Home