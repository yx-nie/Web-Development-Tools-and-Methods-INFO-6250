package com.example.mosaicticket.Repository;

import com.example.mosaicticket.Model.tickets.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDepartureAndArrival(String departure,String arrival);

//    List<Ticket> findByDate(String departure, String arrival, Date date);

}
