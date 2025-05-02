package com.example.scannerapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InventorySyncRequest {
    private String type;
    private List<InventoryItemDTO> items;
}
