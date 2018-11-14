package com.netcracker.hack.dto;

import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.model.Hack;
import java.util.List;
import java.util.UUID;

public class HackDTO {

  private UUID uuid;
  private String title;
  private String startDate;
  private int duration;
  private String place;
  private String site;
  private String description;
  private String auditory;
  private List<TagDTO> tags;

  // private CompanyData company;

  public HackDTO(Hack hack) {
    this.uuid = hack.getUuid();
    this.title = hack.getName();
    this.startDate = hack.getStartDate();
    this.duration = hack.getCountOfDays();
    this.place = hack.getPlace();
    this.site = hack.getSite();
    this.description = hack.getDescription();
    this.auditory = hack.getAuditory();
    this.tags = TagConverter.convertTo( hack.getTags() ) ;
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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
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
 
  public List<TagDTO> getTags() {
    return tags;
  }

  public void setTags(List<TagDTO> tags) {
    this.tags = tags;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    HackDTO other = (HackDTO) obj;

    return this.uuid == other.uuid;
  }

  @Override
  public String toString() {
    return "hackDTO [uuid=" + uuid + ", title=" + title + ", startDate=" + startDate + ", duration="
        + duration + ", place=" + place + ", site=" + site + ", description=" + description
        + ", auditory=" + auditory + "]";
  }



}
