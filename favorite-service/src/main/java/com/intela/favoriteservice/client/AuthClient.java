package com.intela.favoriteservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/v1/auth")
public interface AuthClient {
    @GetExchange("/getLoggedInUserId")
    public ResponseEntity<String> getLoggedInUser();

}
