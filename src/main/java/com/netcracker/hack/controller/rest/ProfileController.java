package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.MessageDTO;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.ProfileService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

  @Autowired
  private ProfileService service;

  @Autowired
  private EventService eventService;

  @ApiOperation("Returns all users")
  @GetMapping
  public List<UserDTO> getAllProfile() {
    return service.getAllProfile();
  }

  @ApiOperation("Returns user by uuid")
  @GetMapping("/me")
  public UserDTO getAuthorizedUserProfile(Principal principal) {

    return service.getUserDTOByLogin(principal.getName());
  }

  @ApiOperation("Returns user by uuid")
  @GetMapping("/{id}")
  public UserDTO getProfile(
      @ApiParam(value = "User's uuid", required = true) @PathVariable UUID id) {
    return service.getUserDTO(id);
  }

  @ApiOperation("Returns user by uuid")
  @GetMapping("/searchUser")
  public List<UserDTO> findUsers(@RequestParam String userLogin, @RequestParam UUID teamID) {

    return service.findUsersByLogin(userLogin, teamID);
  }


  @ApiOperation("Returns user by login")
  @GetMapping("login/{login}")
  public UserDTO getProfileByLogin(@PathVariable String login) {
    return service.getUserDTOByLogin(login);
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

  @ApiOperation("Send message to user")
  @PostMapping("/sendMessage")
  public void sendMessageToUser(
      @ApiParam(value = "User's message", required = true) @RequestBody MessageDTO message) {

    eventService.sendToUser(EventService.MESSAGE_EVENT_TYPE, EventService.OK_EVENT_STATUS,
        message.getSender(), message.getReceiver(), null, null, message.getMessage());

    // TODO: послать и на почту
  }

  @ApiOperation("Send message to admin")
  @PostMapping("/sendErrorMessage")
  public void sendMessageToAdmin(
      @ApiParam(value = "User's message", required = true) @RequestBody MessageDTO message) {

    eventService.sendToAdmin(EventService.MESSAGE_EVENT_TYPE, EventService.OK_EVENT_STATUS,
        message.getSender(), null, null, message.getMessage());

    // TODO: послать и на почту
  }
}
