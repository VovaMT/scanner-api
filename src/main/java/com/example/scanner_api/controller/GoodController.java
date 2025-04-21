package com.example.scanner_api.controller;

import com.example.scanner_api.dto.GoodDTO;
import com.example.scanner_api.service.GoodService;
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
