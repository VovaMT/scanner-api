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

        if (key == null || key.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Device key is missing\"}");
            return false;
        }

        try {
            userService.getLicenseInfo(key);
            return true;
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            int status;

            if (message.equals("User not found")) {
                status = HttpServletResponse.SC_NOT_FOUND;
            } else if (message.equals("No license")) {
                status = HttpServletResponse.SC_UNAUTHORIZED;
            } else {
                status = HttpServletResponse.SC_BAD_REQUEST;
            }

            response.setStatus(status);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + message + "\"}");
            return false;
        }
    }
}
