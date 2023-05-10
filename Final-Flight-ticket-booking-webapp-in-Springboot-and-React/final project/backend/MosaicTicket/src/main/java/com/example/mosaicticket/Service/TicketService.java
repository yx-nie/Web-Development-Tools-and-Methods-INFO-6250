package com.example.mosaicticket.Service;

import com.example.mosaicticket.Model.tickets.Ticket;
import com.example.mosaicticket.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> findTicket(String departure, String arrival){
        List<Ticket> tickets= ticketRepository.findByDepartureAndArrival(departure, arrival);
        return tickets;
    }
//
//    public List<Ticket> findTicketByDate(String departure, String arrival, Date date){
//        List<Ticket> tickets= ticketRepository.findByDate(departure, arrival, date);
//        return tickets;
//    }
}
