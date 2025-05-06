package com.example.scannerapi.controller;

import com.example.scannerapi.dto.InventoryItemDTO;
import com.example.scannerapi.dto.InventorySyncRequest;
import com.example.scannerapi.entity.InventoryItem;
import com.example.scannerapi.repository.InventoryItemRepository;
import com.example.scannerapi.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadInventory(
            @RequestBody List<InventoryItemDTO> items,
            @RequestHeader("Device-Key") String deviceKey
    ) {
        inventoryService.uploadInventory(deviceKey, items);
        return ResponseEntity.ok(Map.of("message", "Інвентаризація збережена"));
    }

}

