package com.example.mosaicticketsuppliers.Repository;


import com.example.mosaicticketsuppliers.Model.ChinaTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChinaTicketRepository extends JpaRepository<ChinaTicket, Long> {
    List<ChinaTicket> findByDepartureAndArrivalAndDate(String departure, String arrival, LocalDateTime date);

    Optional<ChinaTicket> findByIdAndFlightid(Long id, String flightid);

    List<ChinaTicket> findByOperationAirline(String operationAirline);

    Optional<ChinaTicket> findByOperationAirlineAndId(String operationAirline, Long id);

    @Transactional
    Optional<ChinaTicket> deleteByOperationAirlineAndId(String operationAirline, Long id);

    Optional<ChinaTicket> findByFlightid(String flightid);
}
