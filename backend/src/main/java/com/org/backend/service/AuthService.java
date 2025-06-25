package com.org.backend.service;

import com.org.backend.dto.LoginRequest;
import com.org.backend.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
} 