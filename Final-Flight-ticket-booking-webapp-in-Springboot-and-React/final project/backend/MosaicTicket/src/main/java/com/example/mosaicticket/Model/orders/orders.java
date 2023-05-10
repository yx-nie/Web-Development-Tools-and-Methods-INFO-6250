package com.example.mosaicticket.Model.orders;

import com.example.mosaicticket.Model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class orders {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String passportNo;
    private String birthday;
    private String address;
    private String city;
    private Long ticketid;
    private String flightid;
    private String departure;
    private String arrival;
    private LocalDateTime date;
    private String operationAirline;
    private int prices;

    @Column(name = "username", nullable = false)
    private String username;

    public orders() {
    }

    public orders(Long id, String firstname, String lastname, String email, String phone, String passportNo, String birthday, String address, String city, Long ticketid, String flightid, String departure, String arrival, LocalDateTime date, String operationAirline, int prices, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.passportNo = passportNo;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.ticketid = ticketid;
        this.flightid = flightid;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.operationAirline = operationAirline;
        this.prices = prices;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getTicketid() {
        return ticketid;
    }

    public void setTicketid(Long ticketid) {
        this.ticketid = ticketid;
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

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
