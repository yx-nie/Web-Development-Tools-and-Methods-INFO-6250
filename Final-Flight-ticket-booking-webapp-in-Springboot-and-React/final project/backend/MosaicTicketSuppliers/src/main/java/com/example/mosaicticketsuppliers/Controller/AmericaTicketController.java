package com.example.mosaicticketsuppliers.Controller;

import com.example.mosaicticketsuppliers.Model.AmericaTicket;
import com.example.mosaicticketsuppliers.Service.AmericaTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("/")
public class AmericaTicketController {

    @Autowired
    public AmericaTicketService americaTicketService;

    @GetMapping("/AmericaTickets")
    public List<AmericaTicket> getAllTickets(){
        List<AmericaTicket> tickets=americaTicketService.findAllTickets();
        return tickets;
    }

    @GetMapping("/AmericaTickets/{operationAirline}")
    public List<AmericaTicket> getTicketsByAirline(@PathVariable String operationAirline){
        return americaTicketService.findTicketsByAirline(operationAirline);
    }

    @GetMapping("/AmericaTickets/{departure}/{arrival}/{date}")
    public List<AmericaTicket> getTickets(@PathVariable String departure, @PathVariable String arrival,
                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date){
        List<AmericaTicket> tickets=americaTicketService.findTickets(departure, arrival, date);
        return tickets;
    }

    @PutMapping("/AmericaTickets/{orderid}/{flightid}")
    public boolean orderTickets(@PathVariable Long orderid, @PathVariable String flightid){
         return americaTicketService.orderTicket(orderid, flightid);
    }

    @DeleteMapping("/AmericaTickets/{operationAirline}/{id}")
    public ResponseEntity<?> deleteTicketset(@PathVariable String operationAirline, @PathVariable Long id){
        return americaTicketService.deleteTicket(operationAirline, id);
    }

    @PutMapping("/AmericaTickets/update/{operationAirline}/{id}")
    public ResponseEntity<?> updateTicketSet(@PathVariable String operationAirline, @PathVariable Long id,
                                             @RequestBody AmericaTicket americaTicket){

        return americaTicketService.updateTicket(operationAirline, id, americaTicket);
    }

    @PutMapping("/AmericaTickets/update/{operationAirline}/{id}/{quantity}")
    public ResponseEntity<?> updateTicketsetByQuantity(@PathVariable String operationAirline,
                                                       @PathVariable Long id,
                                                       @PathVariable int quantity){
        return americaTicketService.updateTicketByQuantity(operationAirline,id,quantity);
    }

    @PostMapping("/AmericaTickets")
    public void addTicket(@RequestBody AmericaTicket americaTicket){
        String fligtid=americaTicket.getFlightid();

        AmericaTicket ticket= americaTicketService.findByFlightId(fligtid);

        ticket.setDeparture(americaTicket.getDeparture());
        ticket.setArrival(americaTicket.getArrival());
        ticket.setDate(americaTicket.getDate());
        ticket.setOperationAirline(americaTicket.getOperationAirline());
        ticket.setPrice(americaTicket.getPrice());
        ticket.setStock(americaTicket.getStock()+ticket.getStock());
        ticket.setFlightid(americaTicket.getFlightid());
        ticket.setSale(americaTicket.isSale());
        System.out.println(ticket.isSale()+"***************");

        americaTicketService.saveTicket(ticket);
    }
}
