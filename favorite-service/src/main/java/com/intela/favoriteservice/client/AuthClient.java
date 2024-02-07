package com.intela.favoriteservice.client;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AuthClient {
    private final RestClient restClient = RestClient.create();
    private final String uri = "http://localhost:8081";
    public String getUserId(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Please enter a valid token");
        }

        jwtToken = authHeader.split(" ")[1].trim();
        return this.restClient.get()
                .uri(this.uri + "/api/v1/auth/getLoggedInUserId/" + jwtToken)
                .retrieve()
                .body(String.class);
    }
}
