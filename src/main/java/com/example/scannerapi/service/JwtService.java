package com.example.scannerapi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public String generateToken(String key) {
        return Jwts.builder()
                .setSubject(key)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractKey(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
