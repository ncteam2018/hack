package com.netcracker.hack.repository;

import com.netcracker.hack.model.Profile;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, UUID> {
  public Profile findByLogin(String login);
}
