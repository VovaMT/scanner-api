package com.example.scannerapi.controller;

import com.example.scannerapi.dto.InventoryItemDTO;
import com.example.scannerapi.entity.InventoryItem;
import com.example.scannerapi.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryItemRepository inventoryRepository;

    @PostMapping("/sync")
    public ResponseEntity<?> syncInventory(
            @RequestBody List<InventoryItemDTO> items,
            @RequestHeader("Device-Key") String deviceKey
    ) {
        List<InventoryItem> entities = items.stream().map(dto -> {
            InventoryItem item = new InventoryItem();
            item.setGoodCode(dto.getGoodCode());
            item.setQuantity(dto.getQuantity());
            item.setType(dto.getType());
            item.setScannedAt(dto.getScannedAt());
            item.setUpdatedAt(dto.getUpdatedAt());
            item.setDeviceKey(deviceKey);
            return item;
        }).collect(Collectors.toList());

        inventoryRepository.saveAll(entities);

        return ResponseEntity.ok(Collections.singletonMap("message", "Інвентаризацію збережено"));

    }
}
