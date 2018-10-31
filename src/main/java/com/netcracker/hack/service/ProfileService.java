package com.netcracker.hack.service;

import com.netcracker.hack.model.Profile;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProfileService {
    public List<Profile> getAllProfile();

    public Profile getProfile(UUID id);

    public Profile getProfileByLogin(String login);

    public void deleteProfile(UUID id);

    public ResponseEntity<Object> createProfile(Profile profile);

    public ResponseEntity<Object> updateProfile(Profile profile, UUID id);
}
