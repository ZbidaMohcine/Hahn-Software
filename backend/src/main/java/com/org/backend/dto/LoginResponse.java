package com.org.backend.dto;



public record LoginResponse(
        String token,
        String username,
        String email,
        String[] roles,
        boolean authenticated
) {}
