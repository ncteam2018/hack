package com.netcracker.hack.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "teamProfile")
@Immutable
public class TeamProfile {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(
            name = "teamId",
            insertable = false,
            updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(
            name = "profileId",
            insertable = false,
            updatable = false)
    private Profile profile;

    public TeamProfile() {
    }

    public TeamProfile(Team team, Profile profile) {
        this.id.teamId = team.getUuid();
        this.id.profileId = profile.getUuid();

        this.team = team;
        this.profile = profile;

        team.getProfiles().add(this);
        profile.getTeams().add(this);
    }

    public Id getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public Profile getProfile() {
        return profile;
    }

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "team")
        private UUID teamId;

        @Column(name = "profile")
        private UUID profileId;

        public Id() {
        }

        public Id(UUID teamId, UUID profileId) {
            this.teamId = teamId;
            this.profileId = profileId;
        }

        public UUID getTeamId() {
            return teamId;
        }

        public UUID getProfileId() {
            return profileId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return Objects.equals(teamId, id.teamId) &&
                    Objects.equals(profileId, id.profileId);
        }

        @Override
        public int hashCode() {

            return Objects.hash(teamId, profileId);
        }
    }
}
