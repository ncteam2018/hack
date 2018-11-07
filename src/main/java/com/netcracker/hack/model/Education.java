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
@Table(name = "education")
public class Education {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ElementCollection(targetClass = EducationLvl.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "educationLvl", joinColumns = @JoinColumn(name = "educationLvl_id"))
  @Enumerated(EnumType.STRING)
  private Set<EducationLvl> level;

  @Column(name = "institution")
  private String institution;

  @Column(name = "faculty")
  private String faculty;

  @Column(name = "course")
  private Integer course;

  public Education() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Education education = (Education) o;
    return Objects.equals(id, education.id) &&
        level == education.level &&
        Objects.equals(institution, education.institution) &&
        Objects.equals(faculty, education.faculty) &&
        Objects.equals(course, education.course);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, level, institution, faculty, course);
  }
}
