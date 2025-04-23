package com.example.scannerapi.service;

import com.example.scannerapi.dto.LicenseResponse;
import com.example.scannerapi.dto.RegistrationRequest;
import com.example.scannerapi.entity.User;
import com.example.scannerapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testRegister_Successful() {
        RegistrationRequest request = new RegistrationRequest();
        request.setName("Test User");
        request.setKey("fea554a1-9bb3-47a8-8923-db394dae4ebb");

        when(userRepository.existsByKey("fea554a1-9bb3-47a8-8923-db394dae4ebb")).thenReturn(false);

        assertDoesNotThrow(() -> userService.register(request));
    }

    @Test
    void testRegister_KeyAlreadyExists() {
        RegistrationRequest request = new RegistrationRequest();
        request.setName("Test User");
        request.setKey("fea554a1-9bb3-47a8-8923-db394dae4ebb");

        when(userRepository.existsByKey("fea554a1-9bb3-47a8-8923-db394dae4ebb")).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.register(request));
        assertEquals("Key already exists", exception.getMessage());
    }

    @Test
    void testGetLicenseInfo_UserNotFound() {
        when(userRepository.findByKey("fea554a1-9bb3-47a8-8923-db394dae4ebb")).thenReturn(Optional.empty());

        LicenseResponse response = userService.getLicenseInfo("fea554a1-9bb3-47a8-8923-db394dae4ebb");
        assertEquals("USER_NOT_FOUND", response.getStatus());
    }

    @Test
    void testGetLicenseInfo_NoLicense() {
        User user = new User();
        user.setKey("fea554a1-9bb3-47a8-8923-db394dae4ebb");
        user.setKeyLicense("");

        when(userRepository.findByKey("fea554a1-9bb3-47a8-8923-db394dae4ebb")).thenReturn(Optional.of(user));

        LicenseResponse response = userService.getLicenseInfo("fea554a1-9bb3-47a8-8923-db394dae4ebb");
        assertEquals("NO_LICENSE", response.getStatus());
    }

    @Test
    void testGetLicenseInfo_Licensed() {
        User user = new User();
        user.setKey("fea554a1-9bb3-47a8-8923-db394dae4ebb");
        user.setKeyLicense("LICENSE_456");

        when(userRepository.findByKey("fea554a1-9bb3-47a8-8923-db394dae4ebb")).thenReturn(Optional.of(user));

        LicenseResponse response = userService.getLicenseInfo("fea554a1-9bb3-47a8-8923-db394dae4ebb");
        assertEquals("LICENSED", response.getStatus());
        assertEquals("LICENSE_456", response.getKeyLicense());
    }
}
