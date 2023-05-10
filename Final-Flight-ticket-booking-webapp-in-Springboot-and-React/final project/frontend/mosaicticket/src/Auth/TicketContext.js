import { useEffect, useState } from "react";
import React from "react";

export const TicketContext=React.createContext();

export const TicketProvider=({children})=>{
    const [selectedTicket, setSelectedTicket]=useState({});

    return(
        <TicketContext.Provider value={[selectedTicket, setSelectedTicket]}>
            {children}
        </TicketContext.Provider>
    )
}