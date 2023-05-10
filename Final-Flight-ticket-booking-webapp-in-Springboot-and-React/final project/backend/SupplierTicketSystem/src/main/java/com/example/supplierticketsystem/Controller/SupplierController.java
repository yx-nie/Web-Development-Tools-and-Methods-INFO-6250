package com.example.supplierticketsystem.Controller;

import com.example.supplierticketsystem.Service.SupplierService;
import com.example.supplierticketsystem.SupplierModel.Supplier;
import com.example.supplierticketsystem.SupplierRepository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3001")
public class SupplierController {
    @Autowired
    public SupplierRepository supplierRepository;

    @Autowired
    public SupplierService supplierService;

    @Autowired
    public JwtAuthencationController jwtAuthencationController;

    @PostMapping("/register")
    public ResponseEntity<Supplier> newSupplier(@RequestBody Supplier newSupplier){
        Supplier supplier= supplierService.register(newSupplier.getUsername(),
                newSupplier.getEmail(),
                newSupplier.getPassword(),
                newSupplier.getUniqueid(),
                newSupplier.getAddress(),
                newSupplier.getRole());

        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @GetMapping("/suppliers")
    public List<Supplier> findALlSuppliers(){
        List<Supplier> suppliers=supplierService.findAll();
        return suppliers;
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id){
        return supplierService.deleteSupplierById(id);
    }

    @GetMapping("/profile/{username}")
    public Supplier getSupplier(@PathVariable String username){
        Supplier supplier=supplierService.findByUsername(username);
        return supplier;
    }

    @PutMapping("/profile/{id}")
    public Supplier EditSupplier(@RequestBody Supplier supplier, @PathVariable Long id){
        Supplier newsupplier=supplierService.editSupplier(id, supplier);
        return newsupplier;
    }







}
