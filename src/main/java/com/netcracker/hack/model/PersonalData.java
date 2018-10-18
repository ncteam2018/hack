package com.netcracker.hack.model;

import java.util.UUID;

public class PersonalData {
    private UUID uuid;
    private AuthorizationData authorizationData;
    private String firstName;
    private String middleName;
    private String secondName;
    private String gender;
    private String city;
    private String dateOfBirth;

    public PersonalData(UUID uuid, AuthorizationData authorizationData, String firstName, String middleName,
                        String secondName, String gender, String city, String dateOfBirth) {
        this.uuid = uuid;
        this.authorizationData = authorizationData;
        this.firstName = firstName;
        this.middleName = middleName;
        this.secondName = secondName;
        this.gender = gender;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public AuthorizationData getAuthorizationData() {
        return authorizationData;
    }

    public void setAuthorizationData(AuthorizationData authorizationData) {
        this.authorizationData = authorizationData;
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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    @Override
    public String toString() {
        return "PersonalData{" +
                "uuid=" + uuid +
                ", authorizationData=" + authorizationData +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
