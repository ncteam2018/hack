package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.model.CompanyStatus;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Role;
import com.netcracker.hack.model.UserAuthData;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.repository.UserAuthRepository;
import com.netcracker.hack.service.ProfileService;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProfileServiceImpl implements ProfileService {

  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private UserAuthRepository userAuthRepository;

  public List<UserDTO> getAllProfile() {
    return makeListOfUserDTO((List<Profile>) profileRepository.findAll());
  }

  public Profile getProfile(UUID id) {
    Optional<Profile> profile = profileRepository.findById(id);

    return profile.get();
  }

  public UserDTO getUserDTO(UUID id) {
    Optional<Profile> profile = profileRepository.findById(id);

    if (!profile.isPresent()) {
      return new UserDTO();
    }
    return new UserDTO(profile.get(), false);
  }

  public Profile getProfileByLogin(String login) {
    
    return  profileRepository.findByUuid(userAuthRepository.findByLogin(login).getUuid());
  }

  public UserDTO getUserDTOByLogin(String login) {

    return new UserDTO(
        profileRepository.findByUuid(userAuthRepository.findByLogin(login).getUuid()), false);
  }

  public void deleteProfile(UUID id) {
    profileRepository.deleteById(id);
  }

  public ResponseEntity<Object> createProfile(UserDTO profile) {

    profile.setUuid(UUID.randomUUID());
    profile.getUserAuth().setRoles(null);

    Profile savedProfile;
    Set<Role> role = new HashSet<>();

    if (profile.getCompanyData() != null) {
      Set<CompanyStatus> status = new HashSet<>();
      status.add(CompanyStatus.INDIVIDUAL);
      profile.getCompanyData().setStatus(status);
      profile.getCompanyData().setVerification(false);
      role.add(Role.ORGANIZATION);
      // TODO: СОБЫТИЕ ПОДТВЕРДИТЬ КОМПАНИЮ
    } else
      role.add(Role.USER);

    profile.getUserAuth().setRoles(role);
    Profile s = new Profile(profile);
    savedProfile = profileRepository.save(s);

    UserAuthData userAuth = new UserAuthData(profile.getUuid(), profile.getUserAuth().getLogin(),
        profile.getUserAuth().getPassword(), profile.getUserAuth().getRoles());
    userAuthRepository.save(userAuth);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedProfile.getUuid()).toUri();
    return ResponseEntity.created(location).build();
  }

  public ResponseEntity<Object> updateProfile(Profile profile, UUID id) {
    Optional<Profile> profileOptional = profileRepository.findById(id);
    if (!profileOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    profile.setUuid(id);
    profileRepository.save(profile);
    return ResponseEntity.noContent().build();
  }

  private List<UserDTO> makeListOfUserDTO(List<Profile> profiles) {

    ArrayList<UserDTO> users = new ArrayList<>();

    profiles.forEach((Profile profile) -> {
      users.add(new UserDTO(profile,false));
    });

    return users;
  }

}
