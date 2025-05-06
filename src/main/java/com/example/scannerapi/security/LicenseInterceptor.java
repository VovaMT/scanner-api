package com.example.scannerapi.security;

import com.example.scannerapi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LicenseInterceptor implements HandlerInterceptor {

    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getHeader("Device-Key");
        String token = request.getHeader("License-Key");

        if (key == null || key.isEmpty()) {
            return reject(response, HttpServletResponse.SC_UNAUTHORIZED, "Device key is missing");
        }

        if (token == null || token.isEmpty()) {
            return reject(response, HttpServletResponse.SC_UNAUTHORIZED, "License token is missing");
        }

        try {
            userService.validateLicense(key, token); //  основна перевірка
            return true;
        } catch (IllegalArgumentException e) {
            return reject(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    private boolean reject(HttpServletResponse response, int status, String message) throws Exception {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
        return false;
    }
}
