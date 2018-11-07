package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.service.Impl.ProfileServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileRestController {

  private ProfileServiceImpl service;

  @Autowired
  public ProfileRestController(ProfileServiceImpl service) {
    this.service = service;
  }

  @ApiOperation("Returns all users")
  @GetMapping
  public List<UserDTO> getAllProfile() {
    return service.getAllProfile();
  }

  @ApiOperation("Returns user by uuid")
  @GetMapping("/me")
  public UserDTO getAuthorizedUserProfile(Principal principal) {

    return service.getProfileByLogin(principal.getName());
  }

  @ApiOperation("Returns user by uuid")
  @GetMapping("/{id}")
  public UserDTO getProfile(
      @ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
    return service.getProfile(id);
  }

  @ApiOperation("Returns user by login")
  @GetMapping("login/{login}")
  public UserDTO getProfileByLogin(@PathVariable String login) {
    return service.getProfileByLogin(login);
  }

  @ApiOperation("Deletes user profile by uuid")
  @DeleteMapping("/{id}")
  public void deleteProfile(
      @ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
    service.deleteProfile(id);
  }

  @ApiOperation("Adds new user")
  @PostMapping
  public ResponseEntity<Object> createProfile(
      @ApiParam(value = "User's profile", required = true) @RequestBody UserDTO profile) {
    return service.createProfile(profile);
  }

  @ApiOperation("Updates user profile by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateProfile(
      @ApiParam(value = "User's profile", required = true) @RequestBody Profile profile,
      @ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
    return service.updateProfile(profile, id);
  }
}
