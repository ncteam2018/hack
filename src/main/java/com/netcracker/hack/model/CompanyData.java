package com.netcracker.hack.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companyData")
public class CompanyData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private UUID uuid;

  @ElementCollection(targetClass = CompanyStatus.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "statusCompany", joinColumns = @JoinColumn(name = "companyData_id"))
  @Enumerated(EnumType.STRING)
  private Set<CompanyStatus> status;

  @Column(name = "verification")
  private Boolean verification;

  @Column(name = "companyName")
  private String companyName;
  
  @Column(name = "about")
  private String about;
  
  @OneToMany(mappedBy="company")
  private List<Hack> hacks;
  
  public CompanyData() {
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Set<CompanyStatus> getStatus() {
    return status;
  }

  public void setStatus(Set<CompanyStatus> status) {
    this.status = status;
  }

  public Boolean getVerification() {
    return verification;
  }

  public void setVerification(Boolean verification) {
    this.verification = verification;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public List<Hack> getHacks() {
    return hacks;
  }

  public void setHacks(List<Hack> hacks) {
    this.hacks = hacks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompanyData that = (CompanyData) o;
    return Objects.equals(uuid, that.uuid) &&
        status == that.status &&
        Objects.equals(verification, that.verification) &&
        Objects.equals(about, that.about);
  }

  @Override
  public int hashCode() {

    return Objects.hash(uuid, status, verification, about);
  }
}
