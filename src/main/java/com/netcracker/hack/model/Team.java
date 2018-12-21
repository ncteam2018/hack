package com.netcracker.hack.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.netcracker.hack.dto.TeamDTO;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.dto.converter.TagConverter;

@Entity
@Table(name = "team")
public class Team {

  @Id
  @Column(name = "id")
  private UUID uuid;

  @Column(name = "name", length = 300)
  private String name;
  
  @Column(name = "status")
  private String status;      

  @ManyToOne
  private Profile captain;

  @ManyToOne
  private Hack hack;

  @Column(name = "about", length = 1000)
  private String about;

  @ManyToMany
  private List<Tag> skillTags;

  @ManyToMany
  private List<Tag> scopeTags;

  @Column(name = "dateOfPublishing")
  private Date dateOfPublishing;

  @Column(name = "limitOfPeople")
  private Integer limitOfPeople;

  @Column(name = "peopleCount")
  private Integer peopleCount;

  @ManyToMany(fetch=FetchType.EAGER)
  private Set<Profile> teamMembers = new HashSet<>();
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy="team")
  private List<TeamChatMessage> chat;

  public Team(TeamDTO teamDTO) {
    this.uuid = teamDTO.getUuid();
    this.name = teamDTO.getName();
    this.status = teamDTO.getStatus();
    this.captain = new Profile(teamDTO.getCaptain());
    this.hack = new Hack(teamDTO.getHack());
    this.about = teamDTO.getAbout();
    this.skillTags = TagConverter.convertFrom( teamDTO.getSkillTags());
    this.scopeTags = TagConverter.convertFrom( teamDTO.getScopeTags());
    this.dateOfPublishing = teamDTO.getDateOfPublishing();
    this.limitOfPeople = teamDTO.getLimitOfPeople();
    this.peopleCount = teamDTO.getPeopleCount();
    
    for(UserDTO user: teamDTO.getTeamMembers())
      this.teamMembers.add(new Profile(user));
  }

  public Team() {}



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



  public Profile getCaptain() {
    return captain;
  }



  public void setCaptain(Profile captain) {
    this.captain = captain;
  }



  public Hack getHack() {
    return hack;
  }



  public void setHack(Hack hack) {
    this.hack = hack;
  }



  public String getAbout() {
    return about;
  }



  public void setAbout(String about) {
    this.about = about;
  }



  public List<Tag> getSkillTags() {
    return skillTags;
  }



  public void setSkillTags(List<Tag> skillTags) {
    this.skillTags = skillTags;
  }



  public List<Tag> getScopeTags() {
    return scopeTags;
  }



  public void setScopeTags(List<Tag> scopeTags) {
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



  public Set<Profile> getTeamMembers() {
    return teamMembers;
  }



  public void setTeamMembers(Set<Profile> teamMembers) {
    this.teamMembers = teamMembers;
  }
  
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Team team = (Team) o;
    return Objects.equals(uuid, team.uuid);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, name, about, dateOfPublishing, limitOfPeople,
        peopleCount);
  }
}
