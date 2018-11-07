package com.netcracker.hack.service.Impl;

import com.netcracker.hack.model.Team;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.TeamService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamRepository repository;

  public List<Team> getAllTeams() {
    return (List<Team>) repository.findAll();
  }

  public Team getTeam(UUID id) {
    Optional<Team> team = repository.findById(id);
    return team.get();
  }

  public void deleteTeam(UUID id) {
    repository.deleteById(id);
  }

  public ResponseEntity<Object> createTeam(Team team) {
    Team savedTeam = repository.save(team);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedTeam.getUuid()).toUri();
    return ResponseEntity.created(location).build();
  }

  public ResponseEntity<Object> updateTeam(Team team, UUID id) {
    Optional<Team> teamOptional = repository.findById(id);
    if (!teamOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    team.setUuid(id);
    repository.save(team);
    return ResponseEntity.noContent().build();
  }
}

