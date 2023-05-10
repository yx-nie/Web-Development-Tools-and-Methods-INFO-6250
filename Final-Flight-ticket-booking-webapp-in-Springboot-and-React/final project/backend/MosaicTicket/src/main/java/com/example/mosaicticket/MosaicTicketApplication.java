package com.example.mosaicticket;

import com.example.mosaicticket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.mosaicticket.Model"})
public class MosaicTicketApplication {


    public static void main(String[] args) {
        SpringApplication.run(MosaicTicketApplication.class, args);
    }



}
