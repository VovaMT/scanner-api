package com.example.scanner_api.repository;

import com.example.scanner_api.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}