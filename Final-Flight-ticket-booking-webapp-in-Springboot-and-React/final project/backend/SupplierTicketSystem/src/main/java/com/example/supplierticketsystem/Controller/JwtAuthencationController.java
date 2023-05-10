package com.example.supplierticketsystem.Controller;

import com.example.supplierticketsystem.Service.SupplierService;
import com.example.supplierticketsystem.SpringSecurity.JwtTokenUtil;
import com.example.supplierticketsystem.SupplierModel.JwtRequest;
import com.example.supplierticketsystem.SupplierModel.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@CrossOrigin("http://localhost:3001")
public class JwtAuthencationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/auth")
    public void loginByToken() throws Exception{

    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        System.out.println(jwtRequest.getUsername());

        final UserDetails userDetails=supplierService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println(userDetails + "userDetails step");
        final String role=supplierService.findSupplierRole(jwtRequest.getUsername());
        final String token=jwtTokenUtil.generateToken(userDetails.getUsername(),role);
        final Date tokenExpireDate=jwtTokenUtil.getExpirationDateFromToken(token);


        System.out.println(token);

        return ResponseEntity.ok(new JwtResponse(token, tokenExpireDate, role));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println(username + " " + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("authenticated method");
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new Exception("User disabled", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials", e);
        }
    }
}
