package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Team;
import com.netcracker.hack.service.Impl.TeamServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {

  @Autowired
  private TeamServiceImpl service;

  @ApiOperation("Returns all teams")
  @GetMapping
  public List<Team> getAllTeams() {
    return service.getAllTeams();
  }

  @ApiOperation("Returns hack by uuid")
  @GetMapping("/{id}")
  public Team getTeam(@ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return service.getTeam(id);
  }

  @ApiOperation("Deletes hack by uuid")
  @DeleteMapping("/{id}")
  public void deleteTeam(@ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    service.deleteTeam(id);
  }

  @ApiOperation("Adds team by uuid")
  @PostMapping
  public ResponseEntity<Object> createTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody Team team) {
    return service.createTeam(team);
  }

  @ApiOperation("Updates team by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody Team team,
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return service.updateTeam(team, id);
  }
}