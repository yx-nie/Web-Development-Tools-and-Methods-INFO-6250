package com.example.mosaicticketsuppliers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupplierCanadaApplication {
    public static void main(String[] args){
        SpringApplication springApplication= new SpringApplication(SupplierCanadaApplication.class);
        springApplication.setAdditionalProfiles("SupplierCanada");
        springApplication.run(args);

    }
}
