package com.example.scannerapi.service;

import com.example.scannerapi.dto.GoodDTO;
import com.example.scannerapi.entity.Good;
import com.example.scannerapi.entity.User;
import com.example.scannerapi.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoodService {
    private final GoodRepository repository;

    public List<GoodDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private GoodDTO toDto(Good good) {
        GoodDTO dto = new GoodDTO();
        dto.setGoodId(good.getGoodId());
        dto.setBarCode(good.getBarCode());
        dto.setName(good.getName());
        dto.setGoodCode(good.getGoodCode());
        return dto;
    }

    public GoodDTO getGoodByBarCode(String barcode) {
        return repository.findByBarCode(barcode)
                .map(this::toDto)
                .orElse(null);
    }
}
