package com.intela.userservice.controllers;

import com.intela.userservice.client.AuthClient;
import com.intela.userservice.models.Profile;
import com.intela.userservice.request.ProfileRequest;
import com.intela.userservice.services.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final AuthClient authClient;
    @GetMapping("/hello")
    public String hello(HttpServletRequest request){

        return this.authClient.getUserId(request);
    }

    //Create profile
    @PostMapping("/create")
    public ResponseEntity<Profile> createProfile(
            @RequestBody ProfileRequest profileRequest,
            HttpServletRequest request
    ){
        //Get logged user id
        String userId = authClient.getUserId(request);

        //Create Profile object
        var profile = Profile
                .builder()
                .firstname(profileRequest.getFirstname())
                .lastname(profileRequest.getLastname())
                .address(profileRequest.getAddress())
                .mobile(profileRequest.getMobile())
                .userId(userId)
                .build();

        //Save new profile
        return ResponseEntity
                .ok()
                .body(this.profileService.createUserProfile(profile));
    }


    //update profile
    @PutMapping("/update")
    public ResponseEntity<Profile> updateProfile(
            @RequestBody ProfileRequest profileRequest,
            HttpServletRequest request
    ){
        //Get logged user id
        String userId = authClient.getUserId(request);

        //Create Profile object
        var profile = Profile
                .builder()
                .firstname(profileRequest.getFirstname())
                .lastname(profileRequest.getLastname())
                .address(profileRequest.getAddress())
                .mobile(profileRequest.getMobile())
                .build();
        return ResponseEntity
                .ok()
                .body(this.profileService.updateProfileById(profile, userId));
    }


    //fetch all user profiles
    @GetMapping("/fetchAll")
    public ResponseEntity<List<Profile>> fetchAllProducts() {
        return ResponseEntity
                .ok()
                .body(this.profileService.fetchAllProfiles());
    }


    //Fetch user profile by user id
    @GetMapping("/")
    public ResponseEntity<Profile> getProfile(HttpServletRequest request){
        //Get logged user id
        String userId = authClient.getUserId(request);
        return ResponseEntity
                .ok()
                .body(this.profileService.fetchProfileByUserId(userId));
    }


    //Delete user profile by user id
    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable String profileId){
        return ResponseEntity
                .ok()
                .body(this.profileService.deleteProfile(profileId));
    }
}
