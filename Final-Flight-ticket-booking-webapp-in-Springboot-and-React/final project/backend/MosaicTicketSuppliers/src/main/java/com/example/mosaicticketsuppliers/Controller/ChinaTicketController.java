package com.example.mosaicticketsuppliers.Controller;


import com.example.mosaicticketsuppliers.Model.ChinaTicket;
import com.example.mosaicticketsuppliers.Service.ChinaTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("/")
public class ChinaTicketController {

    @Autowired
    public ChinaTicketService chinaTicketService;

    @GetMapping("/ChinaTickets")
    public List<ChinaTicket> getAllTickets(){
        List<ChinaTicket> tickets=chinaTicketService.findAllTickets();
        return tickets;
    }

    @GetMapping("/ChinaTickets/{operationAirline}")
    public List<ChinaTicket> getTicketsByAirline(@PathVariable String operationAirline){
        return chinaTicketService.findTicketsByAirline(operationAirline);
    }

    @GetMapping("/ChinaTickets/{departure}/{arrival}/{date}")
    public List<ChinaTicket> getTickets(@PathVariable String departure, @PathVariable String arrival,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date){
        List<ChinaTicket> tickets=chinaTicketService.findTickets(departure, arrival, date);
        return tickets;
    }

    @PutMapping("/ChinaTickets/{orderid}/{flightid}")
    public boolean orderTickets(@PathVariable Long orderid, @PathVariable String flightid){
        return chinaTicketService.orderTicket(orderid, flightid);
    }

    @DeleteMapping("/ChinaTickets/{operationAirline}/{id}")
    public ResponseEntity<?> deleteTicketset(@PathVariable String operationAirline, @PathVariable Long id){
        return chinaTicketService.deleteTicket(operationAirline, id);
    }

    @PutMapping("/ChinaTickets/update/{operationAirline}/{id}")
    public ResponseEntity<?> updateTicketSet(@PathVariable String operationAirline, @PathVariable Long id,
                                             @RequestBody ChinaTicket chinaTicket){

        return chinaTicketService.updateTicket(operationAirline, id, chinaTicket);
    }

    @PutMapping("/ChinaTickets/update/{operationAirline}/{id}/{quantity}")
    public ResponseEntity<?> updateTicketsetByQuantity(@PathVariable String operationAirline,
                                                       @PathVariable Long id,
                                                       @PathVariable int quantity){
        return chinaTicketService.updateTicketByQuantity(operationAirline,id,quantity);
    }

    @PostMapping("/ChinaTickets")
    public void addTicket(@RequestBody ChinaTicket chinaTicket){
        String fligtid=chinaTicket.getFlightid();

        ChinaTicket ticket= chinaTicketService.findByFlightId(fligtid);

        ticket.setDeparture(chinaTicket.getDeparture());
        ticket.setArrival(chinaTicket.getArrival());
        ticket.setDate(chinaTicket.getDate());
        ticket.setOperationAirline(chinaTicket.getOperationAirline());
        ticket.setPrice(chinaTicket.getPrice());
        ticket.setStock(chinaTicket.getStock()+ticket.getStock());
        ticket.setFlightid(chinaTicket.getFlightid());
        ticket.setSale(chinaTicket.isSale());
        System.out.println(ticket.isSale()+"***************");

        chinaTicketService.saveTicket(ticket);
    }
}
