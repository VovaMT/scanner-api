package com.example.scanner_api.service;

import com.example.scanner_api.dto.GoodDTO;
import com.example.scanner_api.entity.Good;
import com.example.scanner_api.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
