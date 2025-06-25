package com.org.backend.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MockJwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            // Validate mock tokens
            if (isValidMockToken(token)) {
                String username = extractUsernameFromToken(token);
                String[] roles = extractRolesFromToken(token);
                
                List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
                
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
    private boolean isValidMockToken(String token) {
        // Accept our mock tokens
        return "mock-jwt-token-for-user".equals(token) || 
               "mock-jwt-token-for-admin".equals(token);
    }
    
    private String extractUsernameFromToken(String token) {
        if ("mock-jwt-token-for-user".equals(token)) {
            return "user";
        } else if ("mock-jwt-token-for-admin".equals(token)) {
            return "admin";
        }
        return "unknown";
    }
    
    private String[] extractRolesFromToken(String token) {
        if ("mock-jwt-token-for-user".equals(token)) {
            return new String[]{"user"};
        } else if ("mock-jwt-token-for-admin".equals(token)) {
            return new String[]{"admin", "user"};
        }
        return new String[]{};
    }
} 