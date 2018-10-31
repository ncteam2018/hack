package com.netcracker.hack.service.Impl;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository repository;

    public List<Profile> getAllProfile() {
        return (List<Profile>) repository.findAll();
    }

    public Profile getProfile(UUID id) {
        Optional<Profile> profile = repository.findById(id);
        return profile.get();
    }
    public Profile getProfileByLogin(String login){
        return repository.findByLogin(login);
    }

    public void deleteProfile(UUID id) {
        repository.deleteById(id);
    }

    public ResponseEntity<Object> createProfile(Profile profile) {
        Profile savedProfile = repository.save(profile);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProfile.getUuid()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updateProfile(Profile profile, UUID id) {
        Optional<Profile> profileOptional = repository.findById(id);
        if (!profileOptional.isPresent())
            return ResponseEntity.notFound().build();
        profile.setUuid(id);
        repository.save(profile);
        return ResponseEntity.noContent().build();
    }
}
