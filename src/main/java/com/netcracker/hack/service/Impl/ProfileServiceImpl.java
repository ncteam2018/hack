package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.service.ProfileService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired
  private ProfileRepository repository;
  private PasswordEncoder encoder = new BCryptPasswordEncoder();

  public List<UserDTO> getAllProfile() {
    return makeListOfUserDTO((List<Profile>) repository.findAll());
  }

  public UserDTO getProfile(UUID id) {
    Optional<Profile> profile = repository.findById(id);
    return new UserDTO(profile.get());
  }

  public UserDTO getProfileByLogin(String login) {
    return new UserDTO(repository.findByLogin(login));
  }

  public void deleteProfile(UUID id) {
    repository.deleteById(id);
  }

  public ResponseEntity<Object> createProfile(UserDTO profile) {

    Profile savedProfile = repository.save(new Profile(profile, encoder));

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedProfile.getUuid()).toUri();

    return ResponseEntity.created(location).build();
  }

  public ResponseEntity<Object> updateProfile(Profile profile, UUID id) {
    Optional<Profile> profileOptional = repository.findById(id);
    if (!profileOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    profile.setUuid(id);
    repository.save(profile);
    return ResponseEntity.noContent().build();
  }

  private List<UserDTO> makeListOfUserDTO(List<Profile> profiles) {

    ArrayList<UserDTO> users = new ArrayList<>();

    profiles.forEach((Profile profile) -> {
      users.add(new UserDTO(profile));
    });

    return users;
  }

}
