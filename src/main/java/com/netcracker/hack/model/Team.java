package com.netcracker.hack.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "captain_id", unique = true)
    private Profile captain;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hack_id", unique = true)
    private Hack hack;

    @Column(name = "about")
    private String about;

    @Column(name = "idea")
    private String idea;

    @Column(name = "dateOfPublishing")
    private String dateOfPublishing;

    @ElementCollection(targetClass = TeamStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "team_status", joinColumns = @JoinColumn(name = "team_status_id"))
    @Enumerated(EnumType.STRING)
    private Set<TeamStatus> status;

    @Column(name = "limitOfPeople")
    private Integer limitOfPeople;

    @Column(name = "peopleCount")
    private Integer peopleCount;

    @OneToMany(mappedBy = "team")
    private Set<TeamProfile> profiles = new HashSet<>();

    //TODO viewCount???
    //TODO history???


    public Team() {
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

    public Profile getCaptain() {
        return captain;
    }

    public void setCaptain(Profile captain) {
        this.captain = captain;
    }

    public Hack getHack() {
        return hack;
    }

    public void setHack(Hack hack) {
        this.hack = hack;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(String dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    public Set<TeamStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<TeamStatus> status) {
        this.status = status;
    }

    public Integer getLimitOfPeople() {
        return limitOfPeople;
    }

    public void setLimitOfPeople(Integer limitOfPeople) {
        this.limitOfPeople = limitOfPeople;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Set<TeamProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<TeamProfile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(uuid, team.uuid) &&
                Objects.equals(name, team.name) &&
                Objects.equals(captain, team.captain) &&
                Objects.equals(hack, team.hack) &&
                Objects.equals(about, team.about) &&
                Objects.equals(idea, team.idea) &&
                Objects.equals(dateOfPublishing, team.dateOfPublishing) &&
                Objects.equals(status, team.status) &&
                Objects.equals(limitOfPeople, team.limitOfPeople) &&
                Objects.equals(peopleCount, team.peopleCount) &&
                Objects.equals(profiles, team.profiles);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, name, captain, hack, about, idea, dateOfPublishing, status, limitOfPeople, peopleCount, profiles);
    }
}
