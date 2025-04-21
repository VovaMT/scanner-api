package com.example.scannerapi.dto;

import lombok.Data;

@Data
public class GoodDTO {
    private Integer goodId;
    private String barCode;
    private String name;
    private String goodCode;
}
