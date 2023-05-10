package com.example.supplierticketsystem.Service;

import com.example.supplierticketsystem.SupplierModel.Supplier;
import com.example.supplierticketsystem.SupplierRepository.SupplierRepository;
import org.hibernate.boot.query.HbmResultSetMappingDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements UserDetailsService {
    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier supplier=supplierRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
        GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(supplier.getUsername());
        return new org.springframework.security.core.userdetails.User(supplier.getUsername(), supplier.getPassword(), Arrays.asList(grantedAuthority));

    }

    public Supplier register(String username, String email, String password, String uniqueid, String address, String role){
        if(supplierRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        Supplier supplier= new Supplier();
        supplier.setAddress(address);
        supplier.setUsername(username);
        supplier.setUniqueid(uniqueid);
        supplier.setEmail(email);
        supplier.setPassword(new BCryptPasswordEncoder().encode(password));
        supplier.setRole(role);

        supplier=supplierRepository.saveAndFlush(supplier);

        return supplier;

    }

    public String findSupplierRole(String username){
        Optional<Supplier> supplier=supplierRepository.findByUsername(username);
        return supplier.get().getRole();
    }

    public List<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public ResponseEntity<?> deleteSupplierById(Long id){
        Optional<Supplier> supplier=supplierRepository.findById(id);
        if(!supplier.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Supplier> supplier1=supplierRepository.deleteSupplierById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public Supplier findByUsername(String username){
        Optional<Supplier> supplier=supplierRepository.findByUsername(username);
        return supplier.get();
    }

    public Supplier editSupplier(Long id, Supplier newsupplier){
        Supplier supplier=findById(id);
        supplier.setUsername(newsupplier.getUsername());
        supplier.setEmail(newsupplier.getEmail());
        supplier.setAddress(newsupplier.getAddress());
        supplier.setUniqueid(newsupplier.getUniqueid());
        if (newsupplier.getPassword() != null && !newsupplier.getPassword().isEmpty()) {
            supplier.setPassword(new BCryptPasswordEncoder().encode(newsupplier.getPassword()));
        }

        supplier=supplierRepository.saveAndFlush(supplier);
        return supplier;

    }

    public Supplier findById(Long id){
        Optional<Supplier> supplier=supplierRepository.findById(id);
        return supplier.get();
    }


}
