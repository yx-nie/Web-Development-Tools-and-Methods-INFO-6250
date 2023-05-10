package com.example.mosaicticket.Model.user;

import java.util.Date;

public class JwtResponse {
    private final String token;
    private final Date tokenExpireDate;
    private final String role;

    public JwtResponse(String token, Date tokenExpireDate, String role) {
        this.token = token;
        this.tokenExpireDate = tokenExpireDate;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Date getTokenExpireDate() {
        return tokenExpireDate;
    }

    public String getRole() {
        return role;
    }
}
