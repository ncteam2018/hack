package com.netcracker.hack.dto;

import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Team;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TeamDTO {

  private UUID uuid;
  private String name;
  private UserDTO captain;
  private HackDTO hack;
  private String about;
  private List<TagDTO> skillTags;
  private List<TagDTO> scopeTags;
  private Date dateOfPublishing;
  private Integer limitOfPeople;
  private Integer peopleCount;
  private Set<UserDTO> teamMembers = new HashSet<>();

  public TeamDTO(Team team) {
    this.uuid = team.getUuid();
    this.name = team.getName();
    this.captain = new UserDTO(team.getCaptain(),true);
    this.hack = new HackDTO(team.getHack());
    this.about = team.getAbout();
    this.skillTags = TagConverter.convertTo(team.getSkillTags());
    this.scopeTags = TagConverter.convertTo(team.getScopeTags());
    this.dateOfPublishing = team.getDateOfPublishing();
    this.limitOfPeople = team.getLimitOfPeople();
    this.peopleCount = team.getPeopleCount();

    for (Profile user : team.getTeamMembers())
      this.teamMembers.add(new UserDTO(user, true));
  }

  public TeamDTO() {}

  public static Set<TeamDTO> makeSetOfTeamDTO(Set<Team> teams) {

    Set<TeamDTO> teamDTOList = new HashSet<>();

    teams.forEach((Team team) -> {
      teamDTOList.add(new TeamDTO(team));
    });

    return teamDTOList;
  }
  
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDTO getCaptain() {
    return captain;
  }

  public void setCaptain(UserDTO captain) {
    this.captain = captain;
  }

  public HackDTO getHack() {
    return hack;
  }

  public void setHack(HackDTO hack) {
    this.hack = hack;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public List<TagDTO> getSkillTags() {
    return skillTags;
  }

  public void setSkillTags(List<TagDTO> skillTags) {
    this.skillTags = skillTags;
  }

  public List<TagDTO> getScopeTags() {
    return scopeTags;
  }

  public void setScopeTags(List<TagDTO> scopeTags) {
    this.scopeTags = scopeTags;
  }

  public Date getDateOfPublishing() {
    return dateOfPublishing;
  }

  public void setDateOfPublishing(Date dateOfPublishing) {
    this.dateOfPublishing = dateOfPublishing;
  }

  public Integer getLimitOfPeople() {
    return limitOfPeople;
  }

  public void setLimitOfPeople(Integer limitOfPeople) {
    this.limitOfPeople = limitOfPeople;
  }

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public Set<UserDTO> getTeamMembers() {
    return teamMembers;
  }

  public void setTeamMembers(Set<UserDTO> teamMembers) {
    this.teamMembers = teamMembers;
  }

  @Override
  public String toString() {
    return "TeamDTO [uuid=" + uuid + ", name=" + name + ", captain=" + captain + ", hack=" + hack
        + ", about=" + about + ", skillTags=" + skillTags + ", scopeTags=" + scopeTags
        + ", dateOfPublishing=" + dateOfPublishing + ", limitOfPeople=" + limitOfPeople
        + ", peopleCount=" + peopleCount + ", teamMembers=" + teamMembers + "]";
  }



}
