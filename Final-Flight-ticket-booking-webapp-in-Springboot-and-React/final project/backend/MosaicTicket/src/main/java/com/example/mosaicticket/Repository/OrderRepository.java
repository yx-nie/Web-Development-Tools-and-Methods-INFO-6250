package com.example.mosaicticket.Repository;

import com.example.mosaicticket.Model.orders.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<orders, Long> {
    List<orders> findAllByUsername(String username);

}
