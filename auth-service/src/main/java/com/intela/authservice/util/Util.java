package com.intela.authservice.util;

import com.intela.authservice.models.User;
import com.intela.authservice.repositories.UserRepository;
import com.intela.authservice.services.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class Util {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public static User getUserByToken(HttpServletRequest request, JwtService jwtService, UserRepository userRepository) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String userEmail;
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Please enter a valid token");
        }

        jwtToken = authHeader.split(" ")[1].trim();
        userEmail = jwtService.extractUsername(jwtToken);

        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public static String getUserByJwtToken(String jwtToken, UserRepository userRepository, JwtService jwtService) {
        final String userEmail;
        userEmail = jwtService.extractUsername(jwtToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user.getId();
    }
}
