package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.service.ProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {
    @Autowired
    private ProfileService service;

    @ApiOperation("Returns all users")
    @GetMapping
    public List<Profile> getAllProfile() {
        return service.getAllProfile();
    }

    @ApiOperation("Returns user by uuid")
    @GetMapping("/{id}")
    public Profile getProfile(@ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
        return service.getProfile(id);
    }

    @ApiOperation("Deletes user profile by uuid")
    @DeleteMapping("/{id}")
    public void deleteProfile(@ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
        service.deleteProfile(id);
    }

    @ApiOperation("Adds new user")
    @PostMapping
    public ResponseEntity<Object> createProfile(@ApiParam(value = "User's profile", required = true) @RequestBody Profile profile) {
        return service.createProfile(profile);
    }

    @ApiOperation("Updates user profile by uuid")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@ApiParam(value = "User's profile", required = true) @RequestBody Profile profile,
                                                @ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
        return service.updateProfile(profile, id);
    }
}
