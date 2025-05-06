package com.example.scannerapi.config;

import com.example.scannerapi.security.LicenseInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LicenseInterceptor licenseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(licenseInterceptor)
                .addPathPatterns(
                        "/api/goods/**",
                        "/api/inventory/**",
                        "/api/user/**"
                )
                .excludePathPatterns(
                        "/api/user/register",
                        "/api/user/validate",
                        "/api/user/license"
                );
    }
}
