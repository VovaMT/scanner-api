package com.example.scannerapi.controller;

import com.example.scannerapi.dto.GoodDTO;
import com.example.scannerapi.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/good")
    public List<GoodDTO> getAllProducts() {
        return goodService.getAllProducts();
    }
}
