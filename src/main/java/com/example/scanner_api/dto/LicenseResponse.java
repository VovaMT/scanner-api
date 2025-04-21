package com.example.scanner_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LicenseResponse {
    private String status;
    private String keyLicense;
}
