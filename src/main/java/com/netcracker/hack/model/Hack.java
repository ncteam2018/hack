package com.netcracker.hack.model;

import javax.persistence.Entity;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Hack {
    private UUID uuid;
    private String name;
    private String about;
    private ArrayList<Role> roles;
    private ArrayList<Interest> interests;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String location;
    private URL site;
    private Organizer organizer;
    private String sponsors;
    private LocalDateTime dateOfPublishing;
    private boolean verified;
    private ArrayList<TeamNotification> teamNotifications;

    public Hack(UUID uuid, String name, String about, ArrayList<Role> roles, ArrayList<Interest> interests, LocalDateTime dateStart,
                LocalDateTime dateEnd, String location, URL site, Organizer organizer, String sponsors, LocalDateTime dateOfPublishing) {
        this.uuid = uuid;
        this.name = name;
        this.about = about;
        this.roles = roles;
        this.interests = interests;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.location = location;
        this.site = site;
        this.organizer = organizer;
        this.sponsors = sponsors;
        this.dateOfPublishing = dateOfPublishing;
    }
}
