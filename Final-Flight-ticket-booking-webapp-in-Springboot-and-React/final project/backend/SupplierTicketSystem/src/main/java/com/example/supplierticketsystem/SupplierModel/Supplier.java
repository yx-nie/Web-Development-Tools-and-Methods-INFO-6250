package com.example.supplierticketsystem.SupplierModel;

import jakarta.persistence.*;

@Entity
public class Supplier {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String username;
    private String address;
    private String uniqueid;
    private String password;
    private String email;
    private String role;

    public Supplier() {
    }

    public Supplier(Long id, String username, String address, String uniqueid, String password, String email, String role) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.uniqueid = uniqueid;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
