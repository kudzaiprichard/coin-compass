package com.intela.authservice.controllers;


import com.intela.authservice.requestResponse.AuthenticateRequest;
import com.intela.authservice.requestResponse.AuthenticationResponse;
import com.intela.authservice.requestResponse.RegisterRequest;
import com.intela.authservice.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.created(URI.create("")).body(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.accepted()
                .body(authService.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response

    ) throws IOException {
        authService.refreshToken(request, response);
    }


    //Only returns users id
    @GetMapping("/getLoggedInUserId/{token}")
    public String getLoggedInUser(
            @PathVariable String token
    ){

        return this.authService.getLoggedInUserId(token);
    }
}
