package com.example.mosaicticket.Service;

import com.example.mosaicticket.Model.tickets.Ticket;
import com.example.mosaicticket.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<List<Ticket>> getTickets(String departure, String arrival, LocalDateTime date){
        List<List<Ticket>> tickets=supplierRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
        return tickets;
    }

    public boolean sendTicketOrder(Long orderid, String flightid){
        return supplierRepository.findByIdAndFlightid(orderid, flightid);
    }

}
