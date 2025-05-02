package com.example.scannerapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goodCode;
    private Double quantity;
    private Integer type;

    private String scannedAt;
    private String updatedAt;

    private String deviceKey;
}
