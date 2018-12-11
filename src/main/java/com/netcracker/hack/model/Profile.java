package com.netcracker.hack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.hack.dto.UserDTO;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

  @Id
  @Column(name = "uuid")
  private UUID uuid;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "userData_id", unique = true)
  private UserData userData;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "contact_id", unique = true)
  private Contact contact;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "education_id", unique = true)
  private Education education;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "career_id", unique = true)
  private Career career;

  @JsonIgnore
  @ManyToMany(mappedBy = "teamMembers" )
  private Set<Team> teams;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "company_id")
  private CompanyData companyProfile;

  public Profile() {}

  public Profile(UserDTO user) {
    this.uuid = user.getUuid();
    this.userData = new UserData();
    this.userData.setAbout(user.getAbout());
    this.userData.setActive(user.getActive());
    this.userData.setCity(user.getCity());
    this.userData.setDateOfBirth(user.getDateOfBirth());
    this.userData.setfName(user.getFirstName());
    this.userData.setGender(user.getGender());
    this.userData.setlName(user.getLastName());
    this.userData.setmName(user.getMiddleName());

    this.contact = new Contact();
    this.contact.setEmail(user.getEmail());
    this.contact.setPhone(user.getPhone());
    this.contact.setSkype(user.getSkype());

    this.education = new Education();
    this.education.setCourse(user.getCourse());
    this.education.setFaculty(user.getFaculty());
    this.education.setInstitution(user.getInstitution());
    this.education.setLevel(user.getLevel());

    this.career = new Career();
    this.career.setPlaceOfWork(user.getPlaceOfWork());
    this.career.setPosition(user.getPosition());

    this.companyProfile = null;
    if (user.getCompanyData() != null) {
      this.companyProfile = new CompanyData();
      this.companyProfile.setCompanyName(user.getCompanyData().getCompanyName());
      this.companyProfile.setAbout(user.getCompanyData().getAbout());
      this.companyProfile.setStatus(user.getCompanyData().getStatus());
      this.companyProfile.setVerification(user.getCompanyData().getVerification());
    }

    this.teams = null;

  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public UserData getUserData() {
    return userData;
  }

  public void setUserData(UserData userData) {
    this.userData = userData;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public Education getEducation() {
    return education;
  }

  public void setEducation(Education education) {
    this.education = education;
  }

  public Career getCareer() {
    return career;
  }

  public void setCareer(Career career) {
    this.career = career;
  }

  public Set<Team> getTeams() {
    return teams;
  }

  public void setTeams(Set<Team> teams) {
    this.teams = teams;
  }

  public CompanyData getCompanyProfile() {
    return companyProfile;
  }

  public void setCompanyProfile(CompanyData companyProfile) {
    this.companyProfile = companyProfile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(uuid, profile.uuid);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid);
  }
}
