package com.example.scannerapi.repository;

import com.example.scannerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByKey(String key);
    Optional<User> findByKey(String key);
}
