package com.example.supplierticketsystem.SupplierRepository;

import com.example.supplierticketsystem.SpringSecurity.SupplierConfig;
import com.example.supplierticketsystem.SupplierModel.TicketSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.OutputKeys;
import java.util.*;

@Component
public class TicketSetRepository {

    @Autowired
    public SupplierConfig supplierConfig;

    public List<List<TicketSet>> findAll(){
        List<List<TicketSet>> TicketSetList= new ArrayList<>();
        Map<String, String> urls=supplierConfig.getUrls();
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue();
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
            ResponseEntity<List<TicketSet>> response=restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<TicketSet>>() {
            });

            List<TicketSet> tickets=response.getBody();
            TicketSetList.add(tickets);
        }

        return TicketSetList;
    }

    public List<TicketSet> findByAirline(String operationAirline){
        Map<String, String> urls=supplierConfig.getUrls();
        List<TicketSet> allTickets=new ArrayList<>();

        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+"/"+operationAirline;
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
            try {
                ResponseEntity<List<TicketSet>> response=restTemplate.exchange(url, HttpMethod.GET, request, new ParameterizedTypeReference<List<TicketSet>>() {
                });
                if(response!=null && response.hasBody()){
                    List<TicketSet> tickets=response.getBody();
                    allTickets.addAll(tickets);
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        return allTickets.isEmpty()? null:allTickets;
    }

    public ResponseEntity<?> deleteTicketSetByOperationAirlineAndId(String operationAirline, Long id){

        Map<String, String> urls=supplierConfig.getUrls();
        boolean deleteSuccess=false;
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+"/"+operationAirline+"/"+id;
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
            try {
                ResponseEntity<?> response=restTemplate.exchange(url, HttpMethod.DELETE, request, new ParameterizedTypeReference<TicketSet>() {
                });

                if(response.getStatusCode()==HttpStatus.OK){
                    deleteSuccess=true;
                    break;
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(deleteSuccess){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> addTicket(String operationAirline, TicketSet ticketset) {
        Map<String, String> urls = supplierConfig.getUrls();

        String url = null;
        boolean found= false;

        if(urls.containsKey(operationAirline)){
            url=urls.get(operationAirline);
            found=true;
        }
        if (found) {
            RestTemplate restTemplate = new RestTemplate();

            String username = "user";
            String password = "823afe15-4c79-4528-99fe-693b8cd5f9c2";
            String auth = username + ":" + password;
            System.out.println(auth);
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            System.out.println(encodedAuth);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encodedAuth);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<TicketSet> request = new HttpEntity<>(ticketset,headers);
            try {
                ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<TicketSet>() {
                });

                if (response.getStatusCode() == HttpStatus.OK) {
                    return ResponseEntity.status(HttpStatus.OK).build();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> updateTicket(String operationAirline, Long id, TicketSet ticketSet){
        Map<String, String> urls=supplierConfig.getUrls();
        boolean updateSuccess=false;
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+"/"+"update"+"/"+operationAirline+"/"+id;
            RestTemplate restTemplate= new RestTemplate();

            String username="user";
            String password="823afe15-4c79-4528-99fe-693b8cd5f9c2";
            String auth=username+":"+password;
            System.out.println(auth);
            String encodedAuth= Base64.getEncoder().encodeToString(auth.getBytes());
            System.out.println(encodedAuth);

            HttpHeaders headers=new HttpHeaders();
            headers.set("Authorization", "Basic "+encodedAuth);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<TicketSet> request = new HttpEntity<>(ticketSet,headers);
            try {
                ResponseEntity<?> response=restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<TicketSet>() {
                });

                if(response.getStatusCode()==HttpStatus.OK){
                    updateSuccess=true;
                    break;
                }


            }catch (HttpClientErrorException.NotFound e){
                System.out.println("URL not found");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        if(updateSuccess){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> updateTicketByQuantity(String operationAirline, Long id, int quantity){
        Map<String, String> urls=supplierConfig.getUrls();
        boolean updateSuccess=false;
        for(Map.Entry<String, String> entry: urls.entrySet()){
            String url=entry.getValue()+"/"+"update"+"/"+operationAirline+"/"+id +"/"+quantity;
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

            try {
                ResponseEntity<?> response=restTemplate.exchange(url, HttpMethod.PUT, request, new ParameterizedTypeReference<TicketSet>() {
                });

                if(response.getStatusCode()==HttpStatus.OK){
                    updateSuccess=true;
                    break;
                }


            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(updateSuccess){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
