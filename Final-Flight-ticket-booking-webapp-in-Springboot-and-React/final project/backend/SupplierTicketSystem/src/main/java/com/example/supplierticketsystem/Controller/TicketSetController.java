package com.example.supplierticketsystem.Controller;

import com.example.supplierticketsystem.Service.SupplierService;
import com.example.supplierticketsystem.Service.TicketSetService;
import com.example.supplierticketsystem.SupplierModel.TicketSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3001")
public class TicketSetController {

    @Autowired
    public TicketSetService ticketSetService;

    @Autowired
    public SupplierService supplierService;

    @GetMapping("/tickets")
    public List<List<TicketSet>> retrieveAllTickets(){
        return ticketSetService.getAllTickets();
    }

    @GetMapping("/tickets/{operationAirline}")
    public List<TicketSet> retrieveTicketsByAirline(@PathVariable String operationAirline){
        return ticketSetService.getTicketsbyAirline(operationAirline);
    }

    @DeleteMapping("/tickets/{operationAirline}/{id}")
    public ResponseEntity<?> deleteTicketByAirlineAndId(@PathVariable String operationAirline, @PathVariable Long id){
        return ticketSetService.deleteTicket(operationAirline, id);

    }

    @PostMapping("/tickets/{operationAirline}")
    public void AddTicket(@PathVariable String operationAirline, @RequestBody TicketSet ticketSet){
        ticketSetService.addTicket(operationAirline, ticketSet);
    }

    @PutMapping("/tickets/{operationAirline}/{id}")
    public ResponseEntity<?> updateTicketByAirlineAndId(@PathVariable String operationAirline, @PathVariable Long id,
                                           @RequestBody TicketSet ticketSet){
        return ticketSetService.updateTicket(operationAirline,id,ticketSet);

    }

    @PutMapping("/tickets/{operationAirline}/{id}/{quantity}")
    public ResponseEntity<?> updateTicketByAirlineAndIdAndQuantity(@PathVariable String operationAirline,
                                                                   @PathVariable Long id,
                                                                   @PathVariable int quantity){
        return ticketSetService.updateTicketByOrder(operationAirline,id,quantity);
    }

}
