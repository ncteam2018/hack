package com.netcracker.hack.service;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface ProfileService {

  List<UserDTO> getAllProfile();

  UserDTO getUserDTO(UUID id);
  
  Profile getProfile(UUID id);

  UserDTO getUserDTOByLogin(String login);
  
  Profile getProfileByLogin(String login);

  void deleteProfile(UUID id);

  ResponseEntity<Object> createProfile(UserDTO profile);

  ResponseEntity<Object> updateProfile(Profile profile, UUID id);
  
  public List<UserDTO> findUsersByLogin(String userLogin, UUID teamID) ;
}
