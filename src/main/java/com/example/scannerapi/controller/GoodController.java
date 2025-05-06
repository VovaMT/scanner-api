package com.example.scannerapi.controller;

import com.example.scannerapi.dto.GoodDTO;
import com.example.scannerapi.dto.LicenseResponse;
import com.example.scannerapi.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/goods")
    public List<GoodDTO> getAllProducts() {
        return goodService.getAllProducts();
    }

    @GetMapping("/good")
    public ResponseEntity<GoodDTO> getGoodByBarCode(@RequestParam("barCode") String barCode) {
        GoodDTO good = goodService.getGoodByBarCode(barCode);
        if (good != null) {
            return ResponseEntity.ok(good);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
