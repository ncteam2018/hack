package com.netcracker.hack.model;

import com.netcracker.hack.dto.UserDTO;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "profile")
public class Profile {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "login", length = 32, unique = true)
  private String login;

  @Column(name = "password", length = 60)
  private String password;

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "profile_role", joinColumns = @JoinColumn(name = "profile_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "userData_id", unique = true)
  private UserData userData;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "contact_id", unique = true)
  private Contact contact;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "education_id", unique = true)
  private Education education;

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "career_id", unique = true)
  private Career career;

  @OneToMany(mappedBy = "profile")
  private Set<TeamProfile> teams = new HashSet<>();

  @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "company_id")
  private CompanyData companyProfile;
  
  public Profile() {}

  public Profile(UserDTO user, PasswordEncoder encoder) {
    this.login = user.getLogin();
    this.password = encoder.encode(user.getPassword());
    this.roles = user.getRoles();

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
    
    this.companyProfile = new CompanyData();
    this.companyProfile.setCompanyName(user.getCompanyData().getCompanyName());
    this.companyProfile.setAbout(user.getCompanyData().getAbout());
    this.companyProfile.setStatus(user.getCompanyData().getStatus());
    this.companyProfile.setVerification(user.getCompanyData().getVerification());
    
    this.teams = null;
    
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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

  public Set<TeamProfile> getTeams() {
    return teams;
  }

  public void setTeams(Set<TeamProfile> teams) {
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
    return Objects.equals(uuid, profile.uuid) && Objects.equals(login, profile.login)
        && Objects.equals(password, profile.password) && Objects.equals(roles, profile.roles)
        && Objects.equals(userData, profile.userData) && Objects.equals(contact, profile.contact)
        && Objects.equals(education, profile.education) && Objects.equals(teams, profile.teams)
        && Objects.equals(career, profile.career);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, login, password, roles, userData, contact, education, teams, career);
  }
}
