package com.netcracker.hack.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Team {
    private UUID uuid;
    private String name;
    private UUID hackUuid;
    private UUID captainUuid;
    private ArrayList<People> userList;
    private String nameProject;
    private ArrayList<Skill> skillList;
    private ArrayList<Interest> interestList;
    private int size;
    private boolean status;
    private String about;
    private LocalDateTime dateOfPublishing;
    private ArrayList<UserNotification> userNotifications;
}
