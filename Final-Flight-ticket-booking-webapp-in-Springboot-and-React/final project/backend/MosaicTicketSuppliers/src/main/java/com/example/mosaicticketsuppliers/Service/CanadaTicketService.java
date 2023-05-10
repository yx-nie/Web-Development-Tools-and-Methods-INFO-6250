package com.example.mosaicticketsuppliers.Service;

import com.example.mosaicticketsuppliers.Model.AmericaTicket;
import com.example.mosaicticketsuppliers.Model.CanadaTicket;
import com.example.mosaicticketsuppliers.Repository.CanadaTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CanadaTicketService {

    @Autowired
    public CanadaTicketRepository canadaTicketRepository;

    public List<CanadaTicket> findAllTickets(){
        List<CanadaTicket> list=canadaTicketRepository.findAll();
        return list;
    }

    public List<CanadaTicket> findTicketsByAirline(String operationAirline){
        List<CanadaTicket> list=canadaTicketRepository.findByOperationAirline(operationAirline);
        return list;
    }

    public List<CanadaTicket> findTickets(String departure, String arrival, LocalDateTime date){
        List<CanadaTicket> list=canadaTicketRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return list;
    }

    public boolean orderTicket(Long id, String flightid){
        Optional<CanadaTicket> optionalCanadaTicket=canadaTicketRepository.findByIdAndFlightid(id, flightid);
        if(optionalCanadaTicket.isPresent()){
            CanadaTicket ticket=optionalCanadaTicket.get();
            int stock=ticket.getStock();
            if(stock>0){
                ticket.setStock(stock-1);
                canadaTicketRepository.save(ticket);
                return true;
            }
        }

        return false;
    }

    public ResponseEntity<?> deleteTicket(String operationAirline, Long id){
        Optional<CanadaTicket> canadaTicket=canadaTicketRepository.findByOperationAirlineAndId(operationAirline, id);
        if(!canadaTicket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        canadaTicketRepository.deleteByOperationAirlineAndId(operationAirline,id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updateTicket(String operationAirlin, Long id, CanadaTicket canadaTicket){
        Optional<CanadaTicket> ticket=canadaTicketRepository.findByOperationAirlineAndId(operationAirlin,id);
        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CanadaTicket ticketfind= ticket.get();
        ticketfind.setArrival(canadaTicket.getArrival());
        ticketfind.setDeparture(canadaTicket.getDeparture());
        ticketfind.setDate(canadaTicket.getDate());
        ticketfind.setOperationAirline(canadaTicket.getOperationAirline());
        ticketfind.setPrice(canadaTicket.getPrice());
        ticketfind.setStock(canadaTicket.getStock());
        ticketfind.setFlightid(canadaTicket.getFlightid());
        ticketfind.setSale(canadaTicket.isSale());

        CanadaTicket updatedTicket=canadaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public ResponseEntity<?> updateTicketByQuantity(String opeartionAieline, Long id, int quantity){
        Optional<CanadaTicket> ticket=canadaTicketRepository.findByOperationAirlineAndId(opeartionAieline,id
        );

        if(!ticket.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        CanadaTicket ticketfind= ticket.get();
        int newStock=ticketfind.getStock()-quantity;
        ticketfind.setStock(newStock);
        CanadaTicket updatedTicket=canadaTicketRepository.save(ticketfind);
        return ResponseEntity.ok(updatedTicket);
    }

    public void saveTicket(CanadaTicket canadaTicket){
        canadaTicketRepository.saveAndFlush(canadaTicket);
    }

    public CanadaTicket findBYFlightId(String flightid){
        Optional<CanadaTicket> ticket=canadaTicketRepository.findByFlightid(flightid);
        if(!ticket.isPresent()){
            return new CanadaTicket();
        }

        return ticket.get();
    }


}
