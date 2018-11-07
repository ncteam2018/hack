package com.netcracker.hack.model;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

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

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company_id", unique = true)
  private CompanyData company;

  @Column(name = "startDate")
  private String startDate;

  @Column(name = "countOfDays")
  private Integer countOfDays;

  @Column(name = "place")
  private String place;

  @Column(name = "site")
  private String site;

  @Column(name = "auditory")
  private String auditory;

  public Hack() {
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

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
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
    return Objects.equals(uuid, hack.uuid) &&
        Objects.equals(name, hack.name) &&
        Objects.equals(company, hack.company) &&
        Objects.equals(startDate, hack.startDate) &&
        Objects.equals(countOfDays, hack.countOfDays) &&
        Objects.equals(place, hack.place) &&
        Objects.equals(site, hack.site);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, name, company, startDate, countOfDays, place, site);
  }
}