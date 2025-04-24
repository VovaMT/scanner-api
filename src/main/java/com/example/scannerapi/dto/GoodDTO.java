package com.example.scannerapi.dto;

import lombok.Data;

@Data
public class GoodDTO {
    private String name;
    private Boolean inMatrix;
    private Boolean isWeightGood;
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

