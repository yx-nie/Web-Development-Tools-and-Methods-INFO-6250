package com.example.mosaicticket.Controller;

import com.example.mosaicticket.Model.user.JwtRequest;
import com.example.mosaicticket.Model.user.JwtResponse;
import com.example.mosaicticket.Service.UserService;
import com.example.mosaicticket.SpringSecurity.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("http://localhost:3000")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest.getUsername());
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println(userDetails + "userDetails step");
        final String role=userService.findUserRole(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), role);
        final Date tokenExpireDate=jwtTokenUtil.getExpirationDateFromToken(token);

        System.out.println(token);
        return ResponseEntity.ok(new JwtResponse(token, tokenExpireDate, role));

    }

    @PostMapping("/auth")
    public void loginByToken() throws Exception{

    }



    private void authenticate(String username, String password) throws Exception{
        try{
            System.out.println(username+ password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DataAccessException e){
            throw new Exception("User disabled", e);
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }
    }

}
