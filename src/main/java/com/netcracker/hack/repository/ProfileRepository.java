package com.netcracker.hack.repository;

import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Team;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, UUID> {
  public Profile findByUuid(UUID uuid);
  
  public List<Profile> findByUuidInAndTeamsNotContains(List<UUID> usersUUIDs, Team team);
  
}
