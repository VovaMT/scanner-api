package com.example.scannerapi.service;

import com.example.scannerapi.dto.InventoryItemDTO;
import com.example.scannerapi.entity.Inventory;
import com.example.scannerapi.entity.InventoryItem;
import com.example.scannerapi.entity.User;
import com.example.scannerapi.repository.InventoryItemRepository;
import com.example.scannerapi.repository.InventoryRepository;
import com.example.scannerapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryItemRepository itemRepository;

    @Transactional
    public void syncInventory(String deviceKey, List<InventoryItemDTO> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Список товарів порожній");
        }

        User user = userRepository.findByKey(deviceKey)
                .orElseThrow(() -> new RuntimeException("Користувача не знайдено"));

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        LocalDateTime startTime = items.stream()
                .map(i -> LocalDateTime.parse(i.getScannedAt(), formatter))
                .min(Comparator.naturalOrder())
                .orElse(LocalDateTime.now());

        LocalDateTime endTime = items.stream()
                .map(i -> LocalDateTime.parse(i.getUpdatedAt(), formatter))
                .max(Comparator.naturalOrder())
                .orElse(LocalDateTime.now());

        String type = String.valueOf(items.get(0).getType());

        Inventory inventory = Inventory.builder()
                .type(type)
                .startTime(startTime)
                .endTime(endTime)
                .user(user)
                .store(user.getStore())
                .build();

        inventoryRepository.save(inventory);

        List<InventoryItem> entities = items.stream()
                .map(dto -> InventoryItem.builder()
                        .goodCode(dto.getGoodCode())
                        .quantity(dto.getQuantity())
                        .type(dto.getType())
                        .scannedAt(dto.getScannedAt())
                        .updatedAt(dto.getUpdatedAt())
                        .inventory(inventory)
                        .build())
                .toList();

        itemRepository.saveAll(entities);
    }
}

