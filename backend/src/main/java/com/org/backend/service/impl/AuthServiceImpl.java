package com.org.backend.service.impl;

import com.org.backend.dto.LoginRequest;
import com.org.backend.dto.LoginResponse;
import com.org.backend.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // For now, we'll use simple validation
        // In a real implementation, you would validate against Keycloak or your user database
        
        if ("user".equals(loginRequest.getUsername()) && "user123".equals(loginRequest.getPassword())) {
            return new LoginResponse(
                "mock-jwt-token-for-user",
                loginRequest.getUsername(),
                "user@example.com",
                new String[]{"user"},
                true
            );
        } else if ("admin".equals(loginRequest.getUsername()) && "admin123".equals(loginRequest.getPassword())) {
            return new LoginResponse(
                "mock-jwt-token-for-admin",
                loginRequest.getUsername(),
                "admin@example.com",
                new String[]{"admin", "user"},
                true
            );
        }
        
        // Invalid credentials
        return new LoginResponse(
            null,
            null,
            null,
            null,
            false
        );
    }
} 