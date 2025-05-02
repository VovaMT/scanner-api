package com.example.scannerapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "good")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodId;

    private String name;
    private Boolean inMatrix;
    private String unit;
    private String mask;
    private String boxBarCode;
    private Boolean isProduction;
    private Boolean isExcise;
    private Integer priceStatus;
    private Boolean reservationType;
    private String providerName;
    private String barCode;
    private String goodCode;
    private Double price;
    private Double stockCount;
    private Integer blackMailCategory;
    private String endSaleDate;
    private String excise;
}

