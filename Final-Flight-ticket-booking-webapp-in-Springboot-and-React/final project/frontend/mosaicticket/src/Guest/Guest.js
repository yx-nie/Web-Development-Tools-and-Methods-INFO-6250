
function Guest(){
    return(
        <div>

        
        <div className="body bg-primary">
            <div className="container" style={{height:'100px'}}>
                <div className="row" style={{height:'60px'}}>
                    <h3 className='text-end' style={{textAlign:'left'}}>
                        Find Your Flight Ticket
                        <small class="text-body-secondary"> in the lowest prices...</small>
                    </h3>
                </div>
                <form className="row">
                    <div className="col-md-3">
                        <input className="airport" id="airport" placeholder="airport"/>
                    </div>
                    <div className="col-md-3">
                        <input className="startdate" id="startdate" placeholder="startdate"/>
                    </div>
                    <div className="col-md-3">
                        <input className="guest" id="guest" placeholder="guest"/>
                    </div >
                    <input className="col-md-1" type="submit" id="search" value="Search"/>
                </form>
            </div> 
        </div>   
                
        

        </div>
        
    )
}

export default Guest
