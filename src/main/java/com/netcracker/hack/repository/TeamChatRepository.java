package com.netcracker.hack.repository;

import com.netcracker.hack.model.TeamChatMessage;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TeamChatRepository extends CrudRepository<TeamChatMessage, UUID> {
  
  public List<TeamChatMessage> findAllByTeamUuidOrderByDate(UUID teamID);
}
