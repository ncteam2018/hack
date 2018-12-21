package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.TeamDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.ProfileService;
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
  private TeamService teamService;

  @Autowired
  private EventService eventService;

  @Autowired
  private ProfileService profileService;

  @ApiOperation("Returns all teams")
  @GetMapping
  public Page<TeamDTO> getFilteredHacks(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size,
      @RequestParam(name = "sort", required = false) String sortJson,
      @RequestParam(name = "filter", required = false) String filtersJson) {

    return teamService.getFilteredTeams(new PageRequestBuilder(sortJson, page, size, filtersJson));
  }

  @ApiOperation("Returns team by uuid")
  @GetMapping("/{id}")
  public TeamDTO getTeam(@ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return teamService.getTeam(id);
  }

  @ApiOperation("Send request from user")
  @PostMapping("/{teamID}/sendRequest")
  public void sendRequest(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID teamID,
      Principal principal) {

    eventService.sendToUser(EventService.INVITE_EVENT_TYPE, EventService.PROCESSING_EVENT_STATUS,
        profileService.getUserDTOByLogin(principal.getName()).getUuid(),
        teamService.getTeam(teamID).getCaptain().getUuid(), null, teamID, "Запрос на вступление в команду");
    
    eventService.sendNotificationToUser("Пришёл запрос на вступление в команду!", null, teamID, null, teamService.getTeam(teamID).getCaptain().getUuid());
  }

  @ApiOperation("Send invite to user")
  @PostMapping("/{teamID}/sendInvite")
  public void sendInvite(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID teamID,
      @RequestBody UUID invitedUser) {

    eventService.sendToUser(EventService.INVITE_EVENT_TYPE, EventService.PROCESSING_EVENT_STATUS,
        teamService.getTeam(teamID).getCaptain().getUuid(), invitedUser, null, teamID,
        "Приглашение в команду");
    
    eventService.sendNotificationToUser("Вас пригласили в команду!", null, teamID, null, invitedUser);
  }

  @ApiOperation("Add uset to the team")
  @PostMapping("/{teamID}/addUser")
  public ResponseEntity<TeamDTO> addUserToTeam(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID teamID,
      @RequestBody UUID userID) {

    return teamService.addUser(teamID, userID);
  }

  @ApiOperation("remove user from the team")
  @PostMapping("/{teamID}/removeUser")
  public ResponseEntity<TeamDTO> removeUserFromTeam(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable(name = "teamID") UUID teamID,
      @ApiParam(value = "User's uuid",
          required = true) @RequestBody UUID userID) {

    return teamService.removeUser(teamID, userID);
  }

  @ApiOperation("Deletes team by uuid")
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteTeam(
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return teamService.deleteTeam(id);
  }

  @ApiOperation("Adds team by uuid")
  @PostMapping
  public ResponseEntity<TeamDTO> createTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody TeamDTO team) {
    return teamService.createTeam(team);
  }

  @ApiOperation("Updates team by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<TeamDTO> updateTeam(
      @ApiParam(value = "new Team", required = true) @RequestBody TeamDTO team,
      @ApiParam(value = "Team's uuid", required = true) @PathVariable UUID id) {
    return teamService.updateTeam(team, id);
  }
}
