package com.example.mosaicticketsuppliers.Repository;

import com.example.mosaicticketsuppliers.Model.CanadaTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CanadaTicketRepository extends JpaRepository<CanadaTicket, Long> {
    List<CanadaTicket> findByDepartureAndArrivalAndDate(String departure, String arrival, LocalDateTime date);

    Optional<CanadaTicket> findByIdAndFlightid(Long id, String flightid);

    List<CanadaTicket> findByOperationAirline(String operationAirline);

    Optional<CanadaTicket> findByOperationAirlineAndId(String operationAirline, Long id);

    @Transactional
    Optional<CanadaTicket> deleteByOperationAirlineAndId(String operationAirline, Long id);

    Optional<CanadaTicket> findByFlightid(String flightid);

}
