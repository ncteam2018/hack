package com.netcracker.hack.service;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

  List<UserDTO> getAllProfile();

  UserDTO getProfile(UUID id);

  UserDTO getProfileByLogin(String login);

  void deleteProfile(UUID id);

  ResponseEntity<Object> createProfile(UserDTO profile);

  ResponseEntity<Object> updateProfile(Profile profile, UUID id);
}
