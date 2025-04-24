package com.example.scannerapi.repository;

import com.example.scannerapi.entity.Good;
import com.example.scannerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Integer> {
    Optional<Good> findByBarCode(String barCode);
}
