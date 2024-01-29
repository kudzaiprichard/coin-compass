package com.intela.userservice.services;

import com.intela.userservice.models.Profile;
import com.intela.userservice.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    //Create user profile
    private void createUserProfile(Profile profile){
        var _profile = Profile
                .builder()
                .firstname(profile.getFirstname())
                .lastname(profile.getLastname())
                .mobile(profile.getMobile())
                .address(profile.getAddress())
                .userId(profile.getUserId())
                .build();
        this.profileRepository.save(_profile);
    }

    //Update user profile by  id
    private Profile updateProfileById(Profile profile, String id){
        var profileDb = this.profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not available"));

        if(!profile.getFirstname().isBlank()){profileDb.setFirstname(profile.getFirstname());}
        if(!profile.getLastname().isBlank()){profileDb.setLastname(profile.getLastname());}
        if(!profile.getAddress().isBlank()){profileDb.setAddress(profile.getAddress());}
        if(!profile.getMobile().isBlank()){profileDb.setMobile(profile.getMobile());}
        if(!profile.getUserId().isBlank()){profileDb.setUserId(profile.getUserId());}

        return this.profileRepository.save(profileDb);

    }

    //Fetch all user profiles
    private List<Profile> fetchAllProfiles(){
        return this.profileRepository.findAll();
    }

    //Fetch user profile by user id
    private Profile fetchProfileByUserId(String userId){
        return this.profileRepository.findByUserId(userId)
                .orElseThrow(() ->  new RuntimeException("Profile is not available"));
    }

    //Delete user profile by user id
    private void deleteProfile(String id){
        var profile = this.profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile is not available"));

        this.profileRepository.delete(profile);
    }
}
