package com.netcracker.hack.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "companyData")
public class CompanyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ElementCollection(targetClass = CompanyStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "statusCompany", joinColumns = @JoinColumn(name = "companyData_id"))
    @Enumerated(EnumType.STRING)
    private Set<CompanyStatus> status;

    @Column(name = "verification")
    private Boolean verification;

    @Column(name = "about")
    private String about;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
