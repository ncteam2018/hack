package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.TeamDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.Principal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {

  @Autowired
  private TeamService service;

  @ApiOperation("Returns all teams")
  @GetMapping
  public Page<TeamDTO> getFilteredHacks(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size,
      @RequestParam(name = "sort", required = false) String sortJson,
      @RequestParam(name = "filter", required = false) String filtersJson) {

    return service.getFilteredTeams(new PageRequestBuilder(sortJson, page, size, filtersJson));
  }

  @ApiOperation("Returns team by uuid")
  @GetMapping("/{id}")
  public TeamDTO getTeam(@ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return service.getTeam(id);
  }

  @ApiOperation("add uset to the team")
  @PostMapping("/{id}/addMe")
  public ResponseEntity<TeamDTO> addUserToTeam(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id,
      Principal principal) {
    return service.addUser(id, principal.getName());
  }

  @ApiOperation("remove user from the team")
  @PostMapping("/{teamID}/{userID}")
  public ResponseEntity<TeamDTO> removeUserFromTeam(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable(name="teamID") UUID teamID,
      @ApiParam(value = "User's uuid", required = true) @PathVariable(name="userID") UUID userID) {

    return service.removeUser(teamID, userID);
  }

  @ApiOperation("Deletes team by uuid")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTeam(@ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return service.deleteTeam(id);
  }

  @ApiOperation("Adds team by uuid")
  @PostMapping
  public ResponseEntity<TeamDTO> createTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody TeamDTO team) {
    return service.createTeam(team);
  }

  @ApiOperation("Updates team by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<TeamDTO> updateTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody TeamDTO team,
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return service.updateTeam(team, id);
  }
}
