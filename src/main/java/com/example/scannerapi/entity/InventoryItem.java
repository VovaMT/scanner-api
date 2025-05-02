package com.example.scannerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goodCode;
    private Double quantity;
    private Integer type;

    private String scannedAt;
    private String updatedAt;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
}

