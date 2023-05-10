package com.example.mosaicticketsuppliers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupplierAmericaApplication {
    public static void main(String[] args){
        SpringApplication springApplication= new SpringApplication(SupplierAmericaApplication.class);
        springApplication.setAdditionalProfiles("SupplierAmerica");
        springApplication.run(args);
    }
}
