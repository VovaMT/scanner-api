package com.example.scannerapi.repository;

import com.example.scannerapi.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Integer> {
}
