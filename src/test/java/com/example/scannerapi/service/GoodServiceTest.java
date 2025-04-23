package com.example.scannerapi.service;

import com.example.scannerapi.dto.GoodDTO;
import com.example.scannerapi.entity.Good;
import com.example.scannerapi.repository.GoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoodServiceTest {

    private GoodRepository goodRepository;
    private GoodService goodService;

    @BeforeEach
    void setUp() {
        goodRepository = mock(GoodRepository.class);
        goodService = new GoodService(goodRepository);
    }

    @Test
    void testGetAllProducts() {
        Good good = new Good(1, "4820000010001", "Товар", "G001");
        when(goodRepository.findAll()).thenReturn(Collections.singletonList(good));

        List<GoodDTO> result = goodService.getAllProducts();
        assertEquals(1, result.size());
        assertEquals("4820000010001", result.get(0).getBarCode());
        assertEquals("Товар", result.get(0).getName());
        assertEquals("G001", result.get(0).getGoodCode());
    }
}
