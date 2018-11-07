package com.netcracker.hack.service;

import com.netcracker.hack.model.Team;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface TeamService {

  public List<Team> getAllTeams();

  public Team getTeam(UUID id);

  public void deleteTeam(UUID id);

  public ResponseEntity<Object> createTeam(Team team);

  public ResponseEntity<Object> updateTeam(Team team, UUID id);
}
