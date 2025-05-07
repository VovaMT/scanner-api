package com.example.scannerapi.service;

import com.example.scannerapi.dto.RegistrationRequest;
import com.example.scannerapi.entity.Store;
import com.example.scannerapi.entity.User;
import com.example.scannerapi.repository.StoreRepository;
import com.example.scannerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    private final JwtService jwtService;

    public String register(RegistrationRequest request) {
        if (request.getName() == null || request.getKey() == null) {
            throw new IllegalArgumentException("Name and Key are required.");
        }

        if (userRepository.existsByKey(request.getKey())) {
            throw new IllegalArgumentException("Key already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setKey(request.getKey());

        String token = jwtService.generateToken(request.getKey());
        user.setKeyLicense(token);
        userRepository.save(user);

        return token;
    }

    public void validateLicense(String key, String keyLicense) {
        Optional<User> optionalUser = userRepository.findByKey(key);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = optionalUser.get();

        if (user.getKeyLicense() == null || !user.getKeyLicense().equals(keyLicense)) {
            throw new IllegalArgumentException("Invalid license");
        }

        String extractedKey = jwtService.extractKey(keyLicense);
        if (!extractedKey.equals(key)) {
            throw new IllegalArgumentException("Token does not match key");
        }

        if ((user.getStores() == null || user.getStores().isEmpty()) && user.getStore() == null) {
            throw new IllegalArgumentException("User not assigned to any store");
        }

    }

    public Map<String, Object> getUserInfo(String key) {
        User user = userRepository.findByKey(key)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Якщо активного магазину ще нема — вибираємо перший
        if (user.getStore() == null && user.getStores() != null && !user.getStores().isEmpty()) {
            user.setStore(user.getStores().get(0));
            userRepository.save(user);
        }

        if (user.getRole() == 1) {
            return Map.of(
                    "name", user.getName(),
                    "role", user.getRole(),
                    "keyLicense", user.getKeyLicense(),
                    "store", user.getStore()
            );
        } else {
            return Map.of(
                    "name", user.getName(),
                    "role", user.getRole(),
                    "keyLicense", user.getKeyLicense(),
                    "stores", user.getStores()
            );
        }
    }



    public List<Store> getStoresByKey(String key) {
        User user = userRepository.findByKey(key)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Store> stores = user.getStores();

        if (stores.size() == 1 && user.getStore() == null) {
            user.setStore(stores.get(0));
            userRepository.save(user);
        }

        return stores;
    }


    public String getLicenseIfExists(String key) {
        User user = userRepository.findByKey(key)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getKeyLicense() == null || user.getKeyLicense().isEmpty()) {
            throw new IllegalArgumentException("No license");
        }

        return user.getKeyLicense();
    }



    public void setActiveStore(String key, Integer storeId) {
        User user = userRepository.findByKey(key)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean hasAccess = user.getStores().stream()
                .anyMatch(store -> store.getId().equals(storeId));
        if (!hasAccess) {
            throw new IllegalArgumentException("User does not have access to this store");
        }

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found"));

        user.setStore(store);
        userRepository.save(user);
    }



}
