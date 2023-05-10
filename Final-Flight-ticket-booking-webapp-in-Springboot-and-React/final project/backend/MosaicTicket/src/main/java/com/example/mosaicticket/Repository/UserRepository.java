package com.example.mosaicticket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mosaicticket.Model.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    @Transactional
    Optional<User> deleteUserById(Long id);

    boolean existsByUsername(String username);


}
