package com.example.supplierticketsystem.Service;

import com.example.supplierticketsystem.SupplierModel.TicketSet;
import com.example.supplierticketsystem.SupplierRepository.TicketSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketSetService {
    @Autowired
    private TicketSetRepository ticketSetRepository;

    public List<List<TicketSet>> getAllTickets(){
        List<List<TicketSet>> tickets=ticketSetRepository.findAll();
        return tickets;
    }

    public List<TicketSet> getTicketsbyAirline(String operationAirline){
        List<TicketSet> tickets=ticketSetRepository.findByAirline(operationAirline);
        return tickets;
    }

    public ResponseEntity<?> deleteTicket(String operationAirline, Long id){
        return ticketSetRepository.deleteTicketSetByOperationAirlineAndId(operationAirline, id);
    }

    public ResponseEntity<?> addTicket(String operationaAirline, TicketSet ticketSet){
        return ticketSetRepository.addTicket(operationaAirline, ticketSet);
    }

    public ResponseEntity<?> updateTicket(String operationAirline, Long id, TicketSet ticketSet){
        return ticketSetRepository.updateTicket(operationAirline,id,ticketSet);
    }

    public ResponseEntity<?> updateTicketByOrder(String operationAirline, Long id, int quantity){
        return ticketSetRepository.updateTicketByQuantity(operationAirline,id,quantity);
    }
}
