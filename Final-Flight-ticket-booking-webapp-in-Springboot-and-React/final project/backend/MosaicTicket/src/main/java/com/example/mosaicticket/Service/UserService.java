package com.example.mosaicticket.Service;

import com.example.mosaicticket.Exception.UserNotFoundException;
import com.example.mosaicticket.Model.user.User;
import com.example.mosaicticket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        System.out.println(username+"UserDetails");
        User user =userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"+username));
        System.out.println(user.getUsername() +"userRepository");
        GrantedAuthority authority= new SimpleGrantedAuthority(user.getUsername());
        System.out.println(user.getUsername());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(authority));
    }

    public User register(String email, String phone, String username, String password, String role){
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }
        User user= new User();
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(role);
        System.out.println(user+"before respository");

        user=userRepository.saveAndFlush(user);
        System.out.println(user+"user register");
        return user;
    }

    public User findById(Long id){
        Optional<User> user= userRepository.findById(id);
        return user.get();
    }

    public User editUser(Long id, User newuser){
        User user=findById(id);
        user.setUsername(newuser.getUsername());
        user.setPhone(newuser.getPhone());
        user.setEmail(newuser.getEmail());

        if (newuser.getPassword() != null && !newuser.getPassword().isEmpty()) {
            user.setPassword(new BCryptPasswordEncoder().encode(newuser.getPassword()));
        }
        user=userRepository.saveAndFlush(user);
        return user;
    }


    public User findByUsername(String username){
        Optional<User> user= userRepository.findByUsername(username);
        return user.get();
    }

    public String findUserRole(String username){
        Optional<User> user=userRepository.findByUsername(username);
        return user.get().getRole();
    }

    public List<User> findAllUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

    public ResponseEntity<?> deleteUserByUserId(Long id){
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<User> user1=userRepository.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
