package com.intela.userservice.controllers;

import com.intela.userservice.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
}
