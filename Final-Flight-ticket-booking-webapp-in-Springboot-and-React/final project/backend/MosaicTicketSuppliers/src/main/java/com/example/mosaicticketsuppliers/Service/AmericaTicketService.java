package com.example.mosaicticketsuppliers.Service;

import com.example.mosaicticketsuppliers.Model.AmericaTicket;
import com.example.mosaicticketsuppliers.Repository.AmericaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AmericaTicketService {
    @Autowired
    public AmericaTicketRepository americaTicketRepository;

    public List<AmericaTicket> findAllTickets(){
        List<AmericaTicket> list=americaTicketRepository.findAll();
        return list;
    }

    public List<AmericaTicket> findTicketsByAirline(String operationAirline){
        List<AmericaTicket> list=americaTicketRepository.findByOperationAirline(operationAirline);
        return list;
    }

    public List<AmericaTicket> findTickets(String departure, String arrival, LocalDateTime date){
        List<AmericaTicket> tickets=americaTicketRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return tickets;
    }

    public boolean orderTicket(Long id, String flightid){
        Optional<AmericaTicket> optionalAmericaTicket=americaTicketRepository.findByIdAndFlightid(id, flightid);
        if(optionalAmericaTicket.isPresent()) {
            AmericaTicket ticket=optionalAmericaTicket.get();
            int stock = ticket.getStock();
            if (stock > 0) {
                ticket.setStock(stock - 1);
                americaTicketRepository.save(ticket);
                return true;
            }
        }

        return false;
    }

    public ResponseEntity<?> deleteTicket(String operationAirline, Long id){
        Optional<AmericaTicket> americaTicket=americaTicketRepository.findByOperationAirlineAndId(operationAirline,id);
        if(!americaTicket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        americaTicketRepository.deleteByOperationAirlineAndId(operationAirline,id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateTicket(String operationAirlin, Long id, AmericaTicket americaTicket){
        Optional<AmericaTicket> ticket=americaTicketRepository.findByOperationAirlineAndId(operationAirlin,id);
        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        AmericaTicket ticketfind= ticket.get();
        ticketfind.setArrival(americaTicket.getArrival());
        ticketfind.setDeparture(americaTicket.getDeparture());
        ticketfind.setDate(americaTicket.getDate());
        ticketfind.setOperationAirline(americaTicket.getOperationAirline());
        ticketfind.setPrice(americaTicket.getPrice());
        ticketfind.setStock(americaTicket.getStock());
        ticketfind.setFlightid(americaTicket.getFlightid());
        ticketfind.setSale(americaTicket.isSale());

        AmericaTicket updatedTicket=americaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public ResponseEntity<?> updateTicketByQuantity(String opeartionAieline, Long id, int quantity){
        Optional<AmericaTicket> ticket=americaTicketRepository.findByOperationAirlineAndId(opeartionAieline,id
        );

        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        AmericaTicket ticketfind= ticket.get();
        int newStock=ticketfind.getStock()-quantity;
        ticketfind.setStock(newStock);
        AmericaTicket updatedTicket=americaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public void saveTicket(AmericaTicket americaTicket){
        americaTicketRepository.saveAndFlush(americaTicket);
    }

    public AmericaTicket findByFlightId(String flightid){
        Optional<AmericaTicket> ticket=americaTicketRepository.findByFlightid(flightid);
        if(!ticket.isPresent()){
            return new AmericaTicket();
        }

        return ticket.get();
    }
}
