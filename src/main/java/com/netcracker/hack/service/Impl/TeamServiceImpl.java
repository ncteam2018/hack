package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.TeamDTO;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Team;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.ProfileService;
import com.netcracker.hack.service.TagsService;
import com.netcracker.hack.service.TeamService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamRepository teamRepository;
  @Autowired
  private TagsService tagService;
  @Autowired
  private ProfileService profileService;
  @Autowired
  private EventService eventService;
  
  public void deleteTeam(UUID id) {
    teamRepository.deleteById(id);
  }

  public ResponseEntity<Object> updateTeam(Team team, UUID id) {
    Optional<Team> teamOptional = teamRepository.findById(id);
    if (!teamOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    team.setUuid(id);
    teamRepository.save(team);
    return ResponseEntity.noContent().build();
  }

  // 0------------------

  
  
  public TeamDTO getTeam(UUID uuid) {

    return new TeamDTO(teamRepository.findByUuid(uuid));
  }

  public ResponseEntity<TeamDTO> addUser(UUID teamUuid, String login) {
    
    Profile user = profileService.getProfileByLogin(login);
    Team team = teamRepository.findByUuid(teamUuid);
    
    team.getTeamMembers().add(user);
    team.setPeopleCount(team.getPeopleCount()+1);
    teamRepository.save(team);
    
    eventService.createTeamNotifications(teamUuid, "Добавлен новый пользователь - " + user.getUserData().getlName(), user.getUuid());
    
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  
  public ResponseEntity<TeamDTO> removeUser(UUID teamUuid, UUID userUuid) {
    
    Profile user = profileService.getProfile(userUuid);
    Team team = teamRepository.findByUuid(teamUuid);
    
    team.getTeamMembers().remove(user);
    team.setPeopleCount(team.getPeopleCount()-1);
    teamRepository.save(team);
    
    eventService.createTeamNotifications(teamUuid, "Пользователь " + user.getUserData().getlName() + " покинул команду " , user.getUuid());
    
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
  

  public ResponseEntity<TeamDTO> createTeam(TeamDTO team) {

    team.setSkillTags(tagService.verifyTags(team.getSkillTags()));
    team.setScopeTags(tagService.verifyTags(team.getScopeTags()));
    team.setPeopleCount(1);
    team.setDateOfPublishing(Date.valueOf(java.time.LocalDate.now()));
    
    UUID teamUuid = UUID.randomUUID();
    team.setUuid(teamUuid);

    Set<UserDTO> teamMembers = new HashSet<>();
    teamMembers.add(team.getCaptain());
    team.setTeamMembers(teamMembers);

    teamRepository.save(new Team(team));

    return ResponseEntity.status(HttpStatus.CREATED).body(team);
  }


  public Page<TeamDTO> getFilteredTeams(PageRequestBuilder requestBuilder) {
    Page<Team> teamPage;

    if (requestBuilder.isFiltered()) {
      if (requestBuilder.getSkillTags().size() > 0 && requestBuilder.getScopeTags().size() > 0)
        teamPage = teamRepository
            .findDistinctBySkillTagsInAndScopeTagsInAndHackNameContainsAndHackPlaceContains(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getScopeTags(), requestBuilder.getSearchName(),
                requestBuilder.getSearchCityName());
      else if (requestBuilder.getSkillTags().size() > 0)
        teamPage = teamRepository.findDistinctBySkillTagsInAndHackNameContainsAndHackPlaceContains(
            requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
            requestBuilder.getSearchName(), requestBuilder.getSearchCityName());
      else
        teamPage = teamRepository.findDistinctByScopeTagsInAndHackNameContainsAndHackPlaceContains(
            requestBuilder.getPageRequest(), requestBuilder.getScopeTags(),
            requestBuilder.getSearchName(), requestBuilder.getSearchCityName());
    } else
      teamPage = teamRepository.findDistinctByHackNameContainsAndHackPlaceContains(
          requestBuilder.getPageRequest(), requestBuilder.getSearchName(),
          requestBuilder.getSearchCityName());

    PageImpl<TeamDTO> teamDTOPage = new PageImpl<TeamDTO>(makeListOfTeamDTO(teamPage.getContent()),
        teamPage.getPageable(), teamPage.getTotalElements());

    return teamDTOPage;
  }

  private List<TeamDTO> makeListOfTeamDTO(List<Team> teams) {

    ArrayList<TeamDTO> teamDTOList = new ArrayList<>();

    teams.forEach((Team team) -> {
      teamDTOList.add(new TeamDTO(team));
    });

    return teamDTOList;
  }
}

