package com.example.mosaicticketsuppliers.Repository;

import com.example.mosaicticketsuppliers.Model.AmericaTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AmericaTicketRepository extends JpaRepository<AmericaTicket, Long> {
    List<AmericaTicket> findByDepartureAndArrivalAndDate(String departure, String arrival, LocalDateTime date);

    Optional<AmericaTicket> findByIdAndFlightid(Long id, String flightid);

    List<AmericaTicket> findByOperationAirline(String operationAirline);

    Optional<AmericaTicket> findByOperationAirlineAndId(String operationAirline, Long id);

    @Transactional
    Optional<AmericaTicket> deleteByOperationAirlineAndId(String operationAirline, Long id);

    Optional<AmericaTicket> findByFlightid(String flightid);
}
