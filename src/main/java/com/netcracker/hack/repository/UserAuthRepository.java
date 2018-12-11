package com.netcracker.hack.repository;

import com.netcracker.hack.model.UserAuthData;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthRepository extends CrudRepository<UserAuthData, UUID> {
  public UserAuthData findByLogin(String login);
}
