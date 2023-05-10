package com.example.mosaicticket.Controller;

import com.example.mosaicticket.Exception.UserNotFoundException;
import com.example.mosaicticket.Model.user.JwtRequest;
import com.example.mosaicticket.Model.user.User;
import com.example.mosaicticket.Repository.UserRepository;
import com.example.mosaicticket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Autowired
    public JwtAuthenticationController jwtAuthenticationController;

    @PostMapping("/register")
    public ResponseEntity<User> newUser(@RequestBody User newuser){
        User user=userService.register(newuser.getEmail(), newuser.getPhone(), newuser.getUsername(), newuser.getPassword(), newuser.getRole());
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/profile/{username}")
    public User getUser(@PathVariable String username){
        User user=userService.findByUsername(username);
        return user;
    }

    @GetMapping("/users")
    public List<User> getUser(){
        List<User> users=userService.findAllUsers();
        return users;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deletUser(@PathVariable Long id){
        return userService.deleteUserByUserId(id);

    }


    @PutMapping("/profile/{id}")
    public User EditUser(@RequestBody User user, @PathVariable Long id){
        User newuser=userService.editUser(id, user);
        return newuser;
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
//        String username=jwtRequest.getUsername();
//        String password=jwtRequest.getPassword();
//        User user=userService.findByUser(username);
//        String userpassword=user.getPassword();
//        if(userpassword.equals(password)){
//            ResponseEntity<?> response= jwtAuthenticationController.createAuthenticationToken(jwtRequest);
//
//            return response;
//        }
//        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }








//    @GetMapping("/{userid}")
//    public User getUser(@PathVariable Long userid) {
//        return userRepository.findById(userid)
//                .orElseThrow(() -> new UserNotFoundException(userid));
//    }
//
//    @PutMapping("/user/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setEmail(newUser.getEmail());
//                    user.setPassword(newUser.getPassword());
//                    user.setUsername(newUser.getUsername());
//                    user.setPhone(newUser.getPhone());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(()->new UserNotFoundException(id));
//    }
//



}
