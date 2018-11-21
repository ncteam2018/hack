package com.netcracker.hack.dto;

import com.netcracker.hack.model.CompanyData;
import com.netcracker.hack.model.EducationLvl;
import com.netcracker.hack.model.Gender;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Role;
import java.util.Set;
import java.util.UUID;

public class UserDTO {

  private UUID uuid;
  private String login;
  private String password;
  private Set<Role> roles;

  private String firstName;
  private String middleName;
  private String lastName;

  private Set<Gender> gender;
  private String city;
  private String dateOfBirth;
  private Boolean active;
  private String about;

  private String phone;
  private String email;
  private String skype;

  private Set<EducationLvl> level;
  private String institution;
  private String faculty;
  private Integer course;

  private String placeOfWork;
  private String position;

  private CompanyData companyData;

  public UserDTO(Profile profile) {
    this.uuid = profile.getUuid();
    this.login = profile.getLogin();
    this.password = "";
    this.roles = profile.getRoles();

    if (profile.getUserData() != null) {
      this.firstName = profile.getUserData().getfName();
      this.middleName = profile.getUserData().getmName();
      this.lastName = profile.getUserData().getlName();
      this.gender = profile.getUserData().getGender();
      this.city = profile.getUserData().getCity();
      this.dateOfBirth = profile.getUserData().getDateOfBirth();
      this.active = profile.getUserData().getActive();
      this.about = profile.getUserData().getAbout();
    }

    if (profile.getContact() != null) {
      this.phone = profile.getContact().getPhone();
      this.email = profile.getContact().getEmail();
      this.skype = profile.getContact().getSkype();
    }

    if (profile.getEducation() != null) {
      this.level = profile.getEducation().getLevel();
      this.institution = profile.getEducation().getInstitution();
      this.faculty = profile.getEducation().getFaculty();
      this.course = profile.getEducation().getCourse();
    }

    if (profile.getCareer() != null) {
      this.placeOfWork = profile.getCareer().getPlaceOfWork();
      this.position = profile.getCareer().getPosition();
    }

    if (profile.getCompanyProfile() != null) 
      this.companyData = profile.getCompanyProfile();
   
  }

  public UserDTO() {}

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSkype() {
    return skype;
  }

  public void setSkype(String skype) {
    this.skype = skype;
  }

  public Set<EducationLvl> getLevel() {
    return level;
  }

  public void setLevel(Set<EducationLvl> level) {
    this.level = level;
  }

  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public Integer getCourse() {
    return course;
  }

  public void setCourse(Integer course) {
    this.course = course;
  }

  public String getPlaceOfWork() {
    return placeOfWork;
  }

  public void setPlaceOfWork(String placeOfWork) {
    this.placeOfWork = placeOfWork;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public CompanyData getCompanyData() {
    return companyData;
  }

  public void setCompanyData(CompanyData companyData) {
    this.companyData = companyData;
  }

  @Override
  public boolean equals(Object obj) {

    if (obj == null || this.getClass() != obj.getClass()) {
      return false;
    }

    UserDTO other = (UserDTO) obj;

    return this.uuid == other.uuid;
  }

  @Override
  public String toString() {
    return "UserDTO [uuid=" + uuid + ", login=" + login + ", password=" + password + ", roles="
        + roles + ", fisrtName=" + firstName + ", middleName=" + middleName + ", lastName="
        + lastName + ", gender=" + gender + ", city=" + city + ", dateOfBirth=" + dateOfBirth
        + ", active=" + active + ", about=" + about + ", phone=" + phone + ", email=" + email
        + ", skype=" + skype + ", level=" + level + ", institution=" + institution + ", faculty="
        + faculty + ", course=" + course + ", placeOfWork=" + placeOfWork + ", position=" + position
        + "]";
  }



  // private List<Skill> skills;
  // private List<Interest> interests;
}
