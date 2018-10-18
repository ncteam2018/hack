package com.netcracker.hack.model;

public class Education {
    private int id;
    private String educationLevel;
    private String institution;
    private String faculty;
    private int course;

    public Education(int id, String educationLevel, String institution, String faculty, int course) {
        this.id = id;
        this.educationLevel = educationLevel;
        this.institution = institution;
        this.faculty = faculty;
        this.course = course;
    }
}
