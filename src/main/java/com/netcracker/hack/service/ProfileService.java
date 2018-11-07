package com.netcracker.hack.service;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

  public List<UserDTO> getAllProfile();

  public UserDTO getProfile(UUID id);

  public UserDTO getProfileByLogin(String login);

  public void deleteProfile(UUID id);

  public ResponseEntity<Object> createProfile(UserDTO profile);

  public ResponseEntity<Object> updateProfile(Profile profile, UUID id);
}
