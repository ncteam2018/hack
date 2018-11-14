package com.netcracker.hack.model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.TagDTO;

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

  /*
   * @OneToOne(fetch = FetchType.EAGER)
   * 
   * @JoinColumn(name = "company_id", unique = true) private CompanyData company;
   */

  @Column(name = "startDate")
  private String startDate;

  @Column(name = "countOfDays")
  private Integer countOfDays;

  @Column(name = "place")
  private String place;

  @Column(name = "site")
  private String site;

  @Column(name = "description")
  private String description;

  @Column(name = "auditory")
  private String auditory;
  
  
  @ManyToMany
  private List<Tag> tags;

  public Hack() {}

  public Hack(HackDTO hackDTO) {
    this.uuid = hackDTO.getUuid();
    this.name = hackDTO.getTitle();

    // this.company = new CompanyData();
    // company.setStatus(null);
    // company.setVerification(false);
    // company.setAbout("testCompany #1");

    this.tags = TagDTO.convertFrom( hackDTO.getTags() );
    this.startDate = hackDTO.getStartDate();
    this.countOfDays = hackDTO.getDuration();
    this.place = hackDTO.getPlace();
    this.site = hackDTO.getSite();
    this.description = hackDTO.getDescription();
    this.auditory = hackDTO.getAuditory();
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

  /*
   * public CompanyData getCompany() { return company; }
   * 
   * public void setCompany(CompanyData company) { this.company = company; }
   */

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Integer getCountOfDays() {
    return countOfDays;
  }

  public void setCountOfDays(Integer countOfDays) {
    this.countOfDays = countOfDays;
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
  
  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
    this.tags = tags;
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
