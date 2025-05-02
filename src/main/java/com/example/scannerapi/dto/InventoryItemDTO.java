package com.example.scannerapi.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {
    private String goodCode;
    private Double quantity;
    private Integer type;
    private String scannedAt;
    private String updatedAt;
}



