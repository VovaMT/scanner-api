package com.example.scannerapi.controller;

import com.example.scannerapi.dto.LicenseResponse;
import com.example.scannerapi.dto.RegistrationRequest;
import com.example.scannerapi.entity.Store;
import com.example.scannerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registrationUser(@RequestBody RegistrationRequest request) {
        try {
            String token = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("keyLicense", token));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(
            @RequestHeader("Device-Key") String key,
            @RequestHeader("License-Key") String keyLicense
    ) {
        try {
            userService.validateLicense(key, keyLicense);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Device-Key") String key) {
        return ResponseEntity.ok(userService.getUserInfo(key));
    }


    @GetMapping("/license")
    public ResponseEntity<?> getLicense(@RequestParam String key) {
        try {
            String keyLicense = userService.getLicenseIfExists(key);
            return ResponseEntity.ok(Map.of("keyLicense", keyLicense));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }



    @GetMapping("/stores")
    public ResponseEntity<?> getStores(@RequestHeader("Device-Key") String key) {
        try {
            List<Store> stores = userService.getStoresByKey(key);
            return ResponseEntity.ok(stores);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/store/set-active")
    public ResponseEntity<?> setActiveStore(@RequestHeader("Device-Key") String key,
                                            @RequestParam("storeId") Integer storeId) {
        try {
            userService.setActiveStore(key, storeId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

}
