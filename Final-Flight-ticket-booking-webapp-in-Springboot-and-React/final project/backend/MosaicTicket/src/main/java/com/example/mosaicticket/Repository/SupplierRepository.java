package com.example.mosaicticket.Repository;

import com.example.mosaicticket.Model.Supplier.SupplierConfig;
import com.example.mosaicticket.Model.tickets.Ticket;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class SupplierRepository {

    @Autowired
    public SupplierConfig supplierConfig;

    public List<List<Ticket>> findByDepartureAndArrivalAndDate(String departure, String arrival, LocalDateTime date){
        List<List<Ticket>> Ticketslist= new ArrayList<>();
        Map<String, String> urls=supplierConfig.getUrls();
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+departure+"/"+arrival+"/"+date;
            RestTemplate restTemplate= new RestTemplate();

            String username="user";
            String password="823afe15-4c79-4528-99fe-693b8cd5f9c2";
            String auth=username+":"+password;
            System.out.println(auth);
            String encodedAuth= Base64.getEncoder().encodeToString(auth.getBytes());
            System.out.println(encodedAuth);

            HttpHeaders headers=new HttpHeaders();
            headers.set("Authorization", "Basic "+encodedAuth);

            HttpEntity<String> request= new HttpEntity<>(headers);
            ResponseEntity<List<Ticket>> response=restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<Ticket>>() {
            });

            List<Ticket> tickets=response.getBody();
            Ticketslist.add(tickets);
        }
        return Ticketslist;
    }

    public boolean findByIdAndFlightid(Long id, String flightid){

        Map<String, String> urls=supplierConfig.getUrls();
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+id+"/"+flightid;
            RestTemplate restTemplate= new RestTemplate();
            String username="user";
            String password="823afe15-4c79-4528-99fe-693b8cd5f9c2";
            String auth=username+":"+password;
            System.out.println(auth);
            String encodedAuth= Base64.getEncoder().encodeToString(auth.getBytes());
            System.out.println(encodedAuth);

            HttpHeaders headers=new HttpHeaders();
            headers.set("Authorization", "Basic "+encodedAuth);

            HttpEntity<String> request= new HttpEntity<>(headers);

            ResponseEntity<Boolean> response=restTemplate.exchange(url, HttpMethod.PUT, request, Boolean.class);
            boolean ticketfind=response.getBody();

            if(ticketfind){
                return true;
            }
        }

        return false;
    }
}
