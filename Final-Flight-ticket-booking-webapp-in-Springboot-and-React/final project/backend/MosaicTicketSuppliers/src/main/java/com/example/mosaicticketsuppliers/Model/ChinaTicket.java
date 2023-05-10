package com.example.mosaicticketsuppliers.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChinaTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String departure;
    private String arrival;
    private LocalDateTime date;
    private String price;
    private int stock;
    private String operationAirline;
    @Column(unique = true)
    private String flightid;
    private boolean sale;

    public ChinaTicket() {
    }

    public ChinaTicket(Long id, String departure, String arrival, LocalDateTime date, String price, int stock, String operationAirline, String flightid, boolean sale) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.price = price;
        this.stock = stock;
        this.operationAirline = operationAirline;
        this.flightid = flightid;
        this.sale = sale;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getOperationAirline() {
        return operationAirline;
    }

    public void setOperationAirline(String operationAirline) {
        this.operationAirline = operationAirline;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
    }
}
