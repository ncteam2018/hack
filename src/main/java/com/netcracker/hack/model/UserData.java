package com.netcracker.hack.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "userData")
public class UserData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "fName")
  private String fName;

  @Column(name = "mName")
  private String mName;

  @Column(name = "lName")
  private String lName;

  @ElementCollection(targetClass = Gender.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "sex", joinColumns = @JoinColumn(name = "gender_id"))
  @Enumerated(EnumType.STRING)
  private Set<Gender> gender;

  @Column(name = "city")
  private String city;

  @Column(name = "dateOfBirth")
  private String dateOfBirth;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "about")
  private String about;

  public UserData() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getfName() {
    return fName;
  }

  public void setfName(String fName) {
    this.fName = fName;
  }

  public String getmName() {
    return mName;
  }

  public void setmName(String mName) {
    this.mName = mName;
  }

  public String getlName() {
    return lName;
  }

  public void setlName(String lName) {
    this.lName = lName;
  }

  public Set<Gender> getGender() {
    return gender;
  }

  public void setGender(Set<Gender> gender) {
    this.gender = gender;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserData userData = (UserData) o;
    return Objects.equals(id, userData.id) &&
        Objects.equals(fName, userData.fName) &&
        Objects.equals(mName, userData.mName) &&
        Objects.equals(lName, userData.lName) &&
        Objects.equals(gender, userData.gender) &&
        Objects.equals(city, userData.city) &&
        Objects.equals(dateOfBirth, userData.dateOfBirth) &&
        Objects.equals(active, userData.active) &&
        Objects.equals(about, userData.about);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, fName, mName, lName, gender, city, dateOfBirth, active, about);
  }
}
