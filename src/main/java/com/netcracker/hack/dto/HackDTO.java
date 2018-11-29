package com.netcracker.hack.dto;

import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.model.CompanyData;
import com.netcracker.hack.model.Hack;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class HackDTO {

  private UUID uuid;
  private String title;
  private Date startDate;
  private int duration;
  private String place;
  private String placeCoords;
  private String site;
  private String description;
  private String auditory;
  private List<TagDTO> skillTags;
  private List<TagDTO> scopeTags;
  private CompanyData company;
  private Integer teamCountLimit;
  private String status;
  private Integer counterOfRegisteredTeams;
  
  public HackDTO(Hack hack) {
    this.uuid = hack.getUuid();
    this.title = hack.getName();
    this.startDate = hack.getStartDate();
    this.duration = hack.getCountOfDays();
    this.place = hack.getPlace();
    this.placeCoords = hack.getPlaceCoords();
    this.site = hack.getSite();
    this.description = hack.getDescription();
    this.auditory = hack.getAuditory();
    this.skillTags = TagConverter.convertTo(hack.getSkillTags());
    this.scopeTags = TagConverter.convertTo(hack.getScopeTags());
    this.company = hack.getCompany();
    this.status = hack.getStatus();
    this.teamCountLimit = hack.getTeamCountLimit();
    this.counterOfRegisteredTeams = hack.getCounterOfRegisteredTeams();
  }

  public HackDTO() {}

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  
  public String getPlaceCoords() {
    return placeCoords;
  }

  public void setPlaceCoords(String placeCoords) {
    this.placeCoords = placeCoords;
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

  public CompanyData getCompany() {
    return company;
  }

  public void setCompany(CompanyData company) {
    this.company = company;
  }

  public Integer getTeamCountLimit() {
    return teamCountLimit;
  }

  public void setTeamCountLimit(Integer teamCountLimit) {
    this.teamCountLimit = teamCountLimit;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getCounterOfRegisteredTeams() {
    return counterOfRegisteredTeams;
  }

  public void setCounterOfRegisteredTeams(Integer counterOfRegisteredTeams) {
    this.counterOfRegisteredTeams = counterOfRegisteredTeams;
  }

  @Override
  public String toString() {
    return "HackDTO [uuid=" + uuid + ", title=" + title + ", startDate=" + startDate + ", duration="
        + duration + ", place=" + place + ", site=" + site + ", description=" + description
        + ", auditory=" + auditory + ", skillTags=" + skillTags + ", scopeTags=" + scopeTags + "]";
  }

}
