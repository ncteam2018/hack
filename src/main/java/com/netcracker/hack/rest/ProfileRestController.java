package com.netcracker.hack.rest;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProfileRestController {
    @Autowired
    private ProfileService service;

    @GetMapping("/api/profile")
    public List<Profile> retrieveAllProfile() {
        return service.retrieveAllProfile();
    }

    @GetMapping("/api/profile/{id}")
    public Profile retrieveProfile(@PathVariable UUID id) {
        return service.retrieveProfile(id);
    }

    @DeleteMapping("/api/profile/{id}")
    public void deleteProfile(@PathVariable UUID id) {
        service.deleteProfile(id);
    }

    @PostMapping("/api/profile")
    public ResponseEntity<Object> createProfile(@RequestBody Profile profile) {
        return service.createProfile(profile);
    }

    @PutMapping("/api/profile/{id}")
    public ResponseEntity<Object> updateProfile(@RequestBody Profile profile, @PathVariable UUID id) {
        return service.updateProfile(profile, id);
    }
}
