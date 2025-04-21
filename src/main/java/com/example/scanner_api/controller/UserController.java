package com.example.scanner_api.controller;

import com.example.scanner_api.dto.LicenseResponse;
import com.example.scanner_api.dto.RegistrationRequest;
import com.example.scanner_api.service.UserService;
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
    public ResponseEntity<LicenseResponse> checkLicense(@RequestParam("key") String key) {
        LicenseResponse response = userService.getLicenseInfo(key);

        return ResponseEntity.ok(response);
    }

}
