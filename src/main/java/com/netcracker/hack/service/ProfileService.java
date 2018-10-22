package com.netcracker.hack.service;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    public List<Profile> retrieveAllProfile() {
        return (List<Profile>) repository.findAll();
    }

    public Profile retrieveProfile(UUID id){
        Optional<Profile> profile = repository.findById(id);
        return profile.get();
    }

    public void deleteProfile (UUID id){
        repository.deleteById(id);
    }

    public ResponseEntity<Object> createProfile (Profile profile){
        Profile savedProfile = repository.save(profile);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProfile.getUuid()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updateProfile (Profile profile, UUID id){
        Optional<Profile> profileOptional = repository.findById(id);
        if (!profileOptional.isPresent())
            return ResponseEntity.notFound().build();
        profile.setUuid(id);
        repository.save(profile);
        return ResponseEntity.noContent().build();
    }

}
