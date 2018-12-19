package com.netcracker.hack.service;

import com.netcracker.hack.dto.TeamDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TeamService {

  public TeamDTO getTeam(UUID id);

  public ResponseEntity<Object> deleteTeam(UUID id);

  public ResponseEntity<TeamDTO>  createTeam(TeamDTO team);

  public ResponseEntity<TeamDTO> updateTeam(TeamDTO updatedTeam, UUID teamID);
  
  public Page<TeamDTO> getFilteredTeams(PageRequestBuilder builder);

  public ResponseEntity<TeamDTO> addUser(UUID teamID, String login);
  
  public ResponseEntity<TeamDTO> removeUser(UUID teamID, UUID userID);
  
}
