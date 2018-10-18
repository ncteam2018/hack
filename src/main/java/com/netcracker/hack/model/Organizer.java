package com.netcracker.hack.model;

import java.util.ArrayList;
import java.util.UUID;

public class Organizer {
    private UUID uuid;
    private AuthorizationData authorizationData;
    private String name;
    private String about;
    private boolean verified;
    private int status;
    private ArrayList<Hack> hackList;
    private ArrayList<Team> teamList;

}
