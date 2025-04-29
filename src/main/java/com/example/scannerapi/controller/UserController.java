package com.example.scannerapi.controller;

import com.example.scannerapi.dto.LicenseResponse;
import com.example.scannerapi.dto.RegistrationRequest;
import com.example.scannerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registrationUser(@RequestBody RegistrationRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/check-license")
    public ResponseEntity<?> checkLicense(@RequestParam("key") String key) {
        try {
            userService.getLicenseInfo(key);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestParam("key") String key) {
        try {
            return ResponseEntity.ok(userService.getUserInfo(key));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
        }
    }

}
