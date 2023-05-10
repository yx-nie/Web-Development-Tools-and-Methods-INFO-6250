package com.example.mosaicticket.Service;

import com.example.mosaicticket.Model.orders.orders;
import com.example.mosaicticket.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(orders orderlist){
        orderRepository.save(orderlist);
    }

    public List<orders> findOrderslist(String username){
        return orderRepository.findAllByUsername(username);
    }

    public List<orders> findAllOrders(){
        return orderRepository.findAll();
    }

    public ResponseEntity<?> deleteOrderById(Long id){
        Optional<orders> order=orderRepository.findById(id);
        if(!order.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
