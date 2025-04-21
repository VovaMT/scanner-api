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

    public LicenseResponse getLicenseInfo(String key) {
        Optional<User> userOptional = repository.findByKey(key);

        if (userOptional.isEmpty()) {
            return new LicenseResponse("USER_NOT_FOUND", null);
        }

        User user = userOptional.get();

        if (user.getKeyLicense() == null || user.getKeyLicense().isEmpty()) {
            return new LicenseResponse("NO_LICENSE", null);
        }

        return new LicenseResponse("LICENSED", user.getKeyLicense());
    }
}
