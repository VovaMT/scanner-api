package com.example.scannerapi.repository;

import com.example.scannerapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}