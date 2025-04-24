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
        dto.setName(good.getName());
        dto.setInMatrix(good.getInMatrix());
        dto.setIsWeightGood("кг".equalsIgnoreCase(good.getUnit()));
        dto.setUnit(good.getUnit());
        dto.setMask(good.getMask());
        dto.setBoxBarCode(good.getBoxBarCode());
        dto.setIsProduction(good.getIsProduction());
        dto.setIsExcise(good.getIsExcise());
        dto.setPriceStatus(good.getPriceStatus());
        dto.setReservationType(good.getReservationType());
        dto.setProviderName(good.getProviderName());
        dto.setBarCode(good.getBarCode());
        dto.setGoodCode(good.getGoodCode());
        dto.setPrice(good.getPrice());
        dto.setStockCount(good.getStockCount());
        dto.setBlackMailCategory(good.getBlackMailCategory());
        dto.setEndSaleDate(good.getEndSaleDate());
        dto.setExcise(good.getExcise());
        return dto;
    }


    public GoodDTO getGoodByBarCode(String barcode) {
        return repository.findByBarCode(barcode)
                .map(this::toDto)
                .orElse(null);
    }
}
