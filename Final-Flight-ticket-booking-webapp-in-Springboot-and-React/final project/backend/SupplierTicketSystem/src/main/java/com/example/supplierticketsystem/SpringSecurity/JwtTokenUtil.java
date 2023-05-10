package com.example.supplierticketsystem.SpringSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5* 60 *60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username, String role){
        Claims claims= Jwts.claims().setSubject(username);
        claims.put("role", role);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 60*60*1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // retrieve username from jwt token
    public String getUsernameFromToken(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token){
        return getAllClaimsFromToken(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //validate token
    public boolean validateToken(String token, UserDetails user){
        final String username=getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }




}
