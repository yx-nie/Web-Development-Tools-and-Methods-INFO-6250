package com.example.mosaicticketsuppliers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupplierChinaApplication {
    public static void main(String[] args){
        SpringApplication springApplication= new SpringApplication(SupplierChinaApplication.class);
        springApplication.setAdditionalProfiles("SupplierChina");
        springApplication.run(args);

    }
}
