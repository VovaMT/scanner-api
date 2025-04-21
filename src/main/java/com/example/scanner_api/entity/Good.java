package com.example.scanner_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodId;
    private String barCode;
    private String name;
    private String goodCode;

    public Good(String barCode, String name, String goodCode) {
        this.barCode = barCode;
        this.name = name;
        this.goodCode = goodCode;
    }
}
