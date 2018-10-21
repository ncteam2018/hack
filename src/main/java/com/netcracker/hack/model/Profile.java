package com.netcracker.hack.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "login", length = 32)
    private String login;

    @Column(name = "password")
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

    public Profile() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(uuid, profile.uuid) &&
                Objects.equals(login, profile.login) &&
                Objects.equals(password, profile.password) &&
                Objects.equals(roles, profile.roles) &&
                Objects.equals(userData, profile.userData) &&
                Objects.equals(contact, profile.contact) &&
                Objects.equals(education, profile.education) &&
                Objects.equals(teams, profile.teams) &&
                Objects.equals(career, profile.career);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, login, password, roles, userData, contact, education, teams, career);
    }
}

