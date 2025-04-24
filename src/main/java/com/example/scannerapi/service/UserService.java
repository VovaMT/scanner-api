package com.example.scannerapi.service;

import com.example.scannerapi.dto.LicenseResponse;
import com.example.scannerapi.dto.RegistrationRequest;
import com.example.scannerapi.entity.User;
import com.example.scannerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void register(RegistrationRequest request) {

        if (request.getName() == null || request.getKey() == null) {
            throw new IllegalArgumentException("Name and Key are required.");
        }

        if (repository.existsByKey(request.getKey())) {
            throw new IllegalArgumentException("Key already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setKey(request.getKey());

        repository.save(user);
    }

    public void getLicenseInfo(String key) {
        Optional<User> userOptional = repository.findByKey(key);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOptional.get();

        if (user.getKeyLicense() == null || user.getKeyLicense().isEmpty()) {
            throw new IllegalArgumentException("No license");
        }
        // Ліцензія є
    }
}
