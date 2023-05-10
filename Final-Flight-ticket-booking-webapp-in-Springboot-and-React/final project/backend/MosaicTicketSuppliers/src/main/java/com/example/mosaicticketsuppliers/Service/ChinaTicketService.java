package com.example.mosaicticketsuppliers.Service;


import com.example.mosaicticketsuppliers.Model.ChinaTicket;
import com.example.mosaicticketsuppliers.Repository.ChinaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChinaTicketService {
    @Autowired
    public ChinaTicketRepository chinaTicketRepository;

    public List<ChinaTicket> findAllTickets(){
        List<ChinaTicket> list=chinaTicketRepository.findAll();
        return list;
    }

    public List<ChinaTicket> findTicketsByAirline(String operationAirline){
        List<ChinaTicket> list=chinaTicketRepository.findByOperationAirline(operationAirline);
        return list;
    }

    public List<ChinaTicket> findTickets(String departure, String arrival, LocalDateTime date){
        List<ChinaTicket> tickets=chinaTicketRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return tickets;
    }

    public boolean orderTicket(Long id, String flightid){
        Optional<ChinaTicket> optionalChinaTicket=chinaTicketRepository.findByIdAndFlightid(id, flightid);
        if(optionalChinaTicket.isPresent()) {
            ChinaTicket ticket=optionalChinaTicket.get();
            int stock = ticket.getStock();
            if (stock > 0) {
                ticket.setStock(stock - 1);
                chinaTicketRepository.save(ticket);
                return true;
            }
        }

        return false;
    }

    public ResponseEntity<?> deleteTicket(String operationAirline, Long id){
        Optional<ChinaTicket> chinaTicket=chinaTicketRepository.findByOperationAirlineAndId(operationAirline,id);
        if(!chinaTicket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        chinaTicketRepository.deleteByOperationAirlineAndId(operationAirline,id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateTicket(String operationAirlin, Long id, ChinaTicket chinaTicket){
        Optional<ChinaTicket> ticket=chinaTicketRepository.findByOperationAirlineAndId(operationAirlin,id);
        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ChinaTicket ticketfind= ticket.get();
        ticketfind.setArrival(chinaTicket.getArrival());
        ticketfind.setDeparture(chinaTicket.getDeparture());
        ticketfind.setDate(chinaTicket.getDate());
        ticketfind.setOperationAirline(chinaTicket.getOperationAirline());
        ticketfind.setPrice(chinaTicket.getPrice());
        ticketfind.setStock(chinaTicket.getStock());
        ticketfind.setFlightid(chinaTicket.getFlightid());
        ticketfind.setSale(chinaTicket.isSale());

        ChinaTicket updatedTicket=chinaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public ResponseEntity<?> updateTicketByQuantity(String opeartionAieline, Long id, int quantity){
        Optional<ChinaTicket> ticket=chinaTicketRepository.findByOperationAirlineAndId(opeartionAieline,id
        );

        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ChinaTicket ticketfind= ticket.get();
        int newStock=ticketfind.getStock()-quantity;
        ticketfind.setStock(newStock);
        ChinaTicket updatedTicket=chinaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public void saveTicket(ChinaTicket chinaTicket){
        chinaTicketRepository.saveAndFlush(chinaTicket);
    }

    public ChinaTicket findByFlightId(String flightid){
        Optional<ChinaTicket> ticket=chinaTicketRepository.findByFlightid(flightid);
        if(!ticket.isPresent()){
            return new ChinaTicket();
        }

        return ticket.get();
    }
}
