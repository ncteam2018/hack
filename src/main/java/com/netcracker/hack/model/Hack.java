package com.netcracker.hack.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.netcracker.hack.dto.converter.TagConverter;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.hack.dto.HackDTO;

@Entity
@Table(name = "hack")
public class Hack {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id")
  private UUID uuid;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  private CompanyData company;

  @Column(name = "date")
  private Date startDate;

  @Column(name = "countOfDays")
  private Integer countOfDays;

  @Column(name = "place")
  private String place;

  @Column(name = "placeCoords")
  private String placeCoords;

  @Column(name = "site", length = 300)
  private String site;

  @Column(name = "description", length = 1000)
  private String description;

  @Column(name = "auditory")
  private String auditory;

  @Column(name = "status")
  private String status;

  @Column(name = "teamLimit")
  private Integer teamCountLimit;

  @Column(name = "teamCounter")
  private Integer counterOfRegisteredTeams;
 
  @ManyToMany
  private List<Tag> skillTags;

  @ManyToMany
  private List<Tag> scopeTags;

  public Hack() {}

  public Hack(HackDTO hackDTO) {
    this.uuid = hackDTO.getUuid();
    this.name = hackDTO.getTitle();

    this.company = hackDTO.getCompany();

    this.skillTags = TagConverter.convertFrom(hackDTO.getSkillTags());
    this.scopeTags = TagConverter.convertFrom(hackDTO.getScopeTags());
    this.startDate = hackDTO.getStartDate();
    this.countOfDays = hackDTO.getDuration();
    this.place = hackDTO.getPlace();
    this.placeCoords = hackDTO.getPlaceCoords();
    this.site = hackDTO.getSite();
    this.description = hackDTO.getDescription();
    this.auditory = hackDTO.getAuditory();
    if (hackDTO.getStatus() != null)
      this.status = hackDTO.getStatus();
    else
      this.status = "Processing";
    this.counterOfRegisteredTeams = 0;
    this.teamCountLimit = hackDTO.getTeamCountLimit();
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


  public CompanyData getCompany() {
    return company;
  }

  public void setCompany(CompanyData company) {
    this.company = company;
  }


  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Integer getCountOfDays() {
    return countOfDays;
  }

  public void setCountOfDays(Integer countOfDays) {
    this.countOfDays = countOfDays;
  }


  public String getPlaceCoords() {
    return placeCoords;
  }

  public void setPlaceCoords(String placeCoords) {
    this.placeCoords = placeCoords;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuditory() {
    return auditory;
  }

  public void setAuditory(String auditory) {
    this.auditory = auditory;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getTeamCountLimit() {
    return teamCountLimit;
  }

  public void setTeamCountLimit(Integer teamCountLimit) {
    this.teamCountLimit = teamCountLimit;
  }

  public Integer getCounterOfRegisteredTeams() {
    return counterOfRegisteredTeams;
  }

  public void setCounterOfRegisteredTeams(Integer counterOfRegisteredTeams) {
    this.counterOfRegisteredTeams = counterOfRegisteredTeams;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hack hack = (Hack) o;
    return Objects.equals(uuid, hack.uuid) && Objects.equals(name, hack.name)
        && Objects.equals(startDate, hack.startDate)
        && Objects.equals(countOfDays, hack.countOfDays) && Objects.equals(place, hack.place)
        && Objects.equals(site, hack.site);// && Objects.equals(company, hack.company);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, name, startDate, countOfDays, place, site);// ,company);
  }
}
