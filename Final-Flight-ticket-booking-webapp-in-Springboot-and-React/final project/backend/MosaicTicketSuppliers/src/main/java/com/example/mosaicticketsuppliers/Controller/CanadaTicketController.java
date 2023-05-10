package com.example.mosaicticketsuppliers.Controller;

import com.example.mosaicticketsuppliers.Model.CanadaTicket;
import com.example.mosaicticketsuppliers.Service.CanadaTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("/")
public class CanadaTicketController {

    @Autowired
    public CanadaTicketService canadaTicketService;

    @GetMapping("/CanadaTickets")
    public List<CanadaTicket> getAllTickets(){
        List<CanadaTicket> tickets=canadaTicketService.findAllTickets();
        return tickets;
    }

    @GetMapping("/CanadaTickets/{operationAirline}")
    public List<CanadaTicket> getTicketsByAirline(@PathVariable String operationAirline){
        return canadaTicketService.findTicketsByAirline(operationAirline);
    }

    @GetMapping("/CanadaTickets/{departure}/{arrival}/{date}")
    public List<CanadaTicket> getTickets(@PathVariable String departure, @PathVariable String arrival,
                                         @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date){
        List<CanadaTicket> tickets=canadaTicketService.findTickets(departure,arrival,date);
        return tickets;
    }

    @PutMapping("/CanadaTickets/{orderid}/{flightid}")
    public boolean orderTickets(@PathVariable Long orderid, @PathVariable String flightid){
        return canadaTicketService.orderTicket(orderid, flightid);
    }

    @DeleteMapping("/CanadaTickets/{operationAirline}/{id}")
    public ResponseEntity<?> deleteTicketSet(@PathVariable String operationAirline, @PathVariable Long id){
        return canadaTicketService.deleteTicket(operationAirline, id);
    }

    @PutMapping("/CanadaTickets/update/{operationAirline}/{id}")
    public ResponseEntity<?> updateTicketSet(@PathVariable String operationAirline, @PathVariable Long id,
                                             @RequestBody CanadaTicket canadaTicket){
        return canadaTicketService.updateTicket(operationAirline,id,canadaTicket);
    }

    @PutMapping("/CanadaTickets/update/{operationAirline}/{id}/{quantity}")
    public ResponseEntity<?> updateTicketsetByQuantity(@PathVariable String operationAirline,
                                                       @PathVariable Long id,
                                                       @PathVariable int quantity){
        return canadaTicketService.updateTicketByQuantity(operationAirline,id,quantity);
    }

    @PostMapping("/CanadaTickets")
    public void addTicket(@RequestBody CanadaTicket ticket){
        String flightid=ticket.getFlightid();

        CanadaTicket canadaTicket= canadaTicketService.findBYFlightId(flightid);

        canadaTicket.setDeparture(ticket.getDeparture());
        canadaTicket.setArrival(ticket.getArrival());
        canadaTicket.setDate(ticket.getDate());
        canadaTicket.setPrice(ticket.getPrice());
        canadaTicket.setStock(ticket.getStock()+canadaTicket.getStock());
        canadaTicket.setOperationAirline(ticket.getOperationAirline());
        canadaTicket.setFlightid(ticket.getFlightid());
        canadaTicket.setSale(ticket.isSale());

        canadaTicketService.saveTicket(canadaTicket);
    }
}
