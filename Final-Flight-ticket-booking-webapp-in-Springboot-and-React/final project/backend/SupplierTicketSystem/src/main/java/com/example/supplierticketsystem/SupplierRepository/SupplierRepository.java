package com.example.supplierticketsystem.SupplierRepository;

import com.example.supplierticketsystem.SupplierModel.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByUsername(String username);
    Optional<Supplier> findById(Long id);
    @Transactional
    Optional<Supplier> deleteSupplierById(Long id);

    boolean existsByUsername(String username);
}
