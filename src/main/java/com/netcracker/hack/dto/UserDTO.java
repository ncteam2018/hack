package com.netcracker.hack.dto;

import com.netcracker.hack.model.EducationLvl;
import com.netcracker.hack.model.Gender;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Role;
import java.util.Set;

public class UserDTO {

  private String login;
  private String password;
  private Set<Role> roles;

  private String fName;
  private String mName;
  private String lName;
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

  public UserDTO(Profile profile) {
    this.login = profile.getLogin();
    this.password = "";
    this.roles = profile.getRoles();

    if (profile.getUserData() != null) {
      this.fName = profile.getUserData().getfName();
      this.mName = profile.getUserData().getmName();
      this.lName = profile.getUserData().getlName();
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

  }

  public UserDTO() {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((about == null) ? 0 : about.hashCode());
    result = prime * result + ((active == null) ? 0 : active.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((course == null) ? 0 : course.hashCode());
    result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((fName == null) ? 0 : fName.hashCode());
    result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((institution == null) ? 0 : institution.hashCode());
    result = prime * result + ((lName == null) ? 0 : lName.hashCode());
    result = prime * result + ((level == null) ? 0 : level.hashCode());
    result = prime * result + ((login == null) ? 0 : login.hashCode());
    result = prime * result + ((mName == null) ? 0 : mName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((phone == null) ? 0 : phone.hashCode());
    result = prime * result + ((placeOfWork == null) ? 0 : placeOfWork.hashCode());
    result = prime * result + ((position == null) ? 0 : position.hashCode());
    result = prime * result + ((roles == null) ? 0 : roles.hashCode());
    result = prime * result + ((skype == null) ? 0 : skype.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UserDTO other = (UserDTO) obj;
    if (about == null) {
      if (other.about != null) {
        return false;
      }
    } else if (!about.equals(other.about)) {
      return false;
    }
    if (active == null) {
      if (other.active != null) {
        return false;
      }
    } else if (!active.equals(other.active)) {
      return false;
    }
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (course == null) {
      if (other.course != null) {
        return false;
      }
    } else if (!course.equals(other.course)) {
      return false;
    }
    if (dateOfBirth == null) {
      if (other.dateOfBirth != null) {
        return false;
      }
    } else if (!dateOfBirth.equals(other.dateOfBirth)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (fName == null) {
      if (other.fName != null) {
        return false;
      }
    } else if (!fName.equals(other.fName)) {
      return false;
    }
    if (faculty == null) {
      if (other.faculty != null) {
        return false;
      }
    } else if (!faculty.equals(other.faculty)) {
      return false;
    }
    if (gender == null) {
      if (other.gender != null) {
        return false;
      }
    } else if (!gender.equals(other.gender)) {
      return false;
    }
    if (institution == null) {
      if (other.institution != null) {
        return false;
      }
    } else if (!institution.equals(other.institution)) {
      return false;
    }
    if (lName == null) {
      if (other.lName != null) {
        return false;
      }
    } else if (!lName.equals(other.lName)) {
      return false;
    }
    if (level == null) {
      if (other.level != null) {
        return false;
      }
    } else if (!level.equals(other.level)) {
      return false;
    }
    if (login == null) {
      if (other.login != null) {
        return false;
      }
    } else if (!login.equals(other.login)) {
      return false;
    }
    if (mName == null) {
      if (other.mName != null) {
        return false;
      }
    } else if (!mName.equals(other.mName)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (phone == null) {
      if (other.phone != null) {
        return false;
      }
    } else if (!phone.equals(other.phone)) {
      return false;
    }
    if (placeOfWork == null) {
      if (other.placeOfWork != null) {
        return false;
      }
    } else if (!placeOfWork.equals(other.placeOfWork)) {
      return false;
    }
    if (position == null) {
      if (other.position != null) {
        return false;
      }
    } else if (!position.equals(other.position)) {
      return false;
    }
    if (roles == null) {
      if (other.roles != null) {
        return false;
      }
    } else if (!roles.equals(other.roles)) {
      return false;
    }
    if (skype == null) {
      if (other.skype != null) {
        return false;
      }
    } else if (!skype.equals(other.skype)) {
      return false;
    }
    return true;
  }

  // private List<Skill> skills;
  // private List<Interest> interests;
}
