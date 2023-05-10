package com.example.supplierticketsystem.SupplierModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;

public class TicketSet {

    private Long id;
    private String flightid;
    private String departure;
    private String arrival;
    private LocalDateTime date;
    private String operationAirline;
    private int price;
    private int stock;
    private boolean sale;

    public TicketSet() {
    }

    public TicketSet(Long id, String flightid, String departure, String arrival, LocalDateTime date, String operationAirline, int price, int stock, boolean sale) {
        this.id = id;
        this.flightid = flightid;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.operationAirline = operationAirline;
        this.price = price;
        this.stock = stock;
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
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

    public String getOperationAirline() {
        return operationAirline;
    }

    public void setOperationAirline(String operationAirline) {
        this.operationAirline = operationAirline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
