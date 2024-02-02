package com.intela.userservice.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/auth")
public interface AuthClient {
    @GetExchange("/getLoggedInUserId")
    public ResponseEntity<String> getLoggedInUser();

}
