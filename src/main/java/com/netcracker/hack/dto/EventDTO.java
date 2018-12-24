package com.netcracker.hack.dto;

import java.sql.Date;
import java.util.UUID;
import com.netcracker.hack.model.Event;
import com.netcracker.hack.model.Team;

public class EventDTO {

  private UUID id;
  private UserDTO sender;
  private UserDTO receiver;
  private String type;
  private String status;
  private Date dateOfCreation;
  private Date dateOfUpdate;
  private HackDTO resourceHackReference;
  private Team resourceTeamReference; 
  private String message;

  public EventDTO(Event event) {
    this.id = event.getId();
    this.sender = new UserDTO(event.getSender(),false);
    this.receiver = new UserDTO(event.getReceiver(),false);
    this.type = event.getType().getType();
    this.status = event.getStatus().getStatus();
    this.dateOfCreation = event.getDateOfCreation();
    this.dateOfUpdate = event.getDateOfUpdate();
    this.resourceHackReference = new HackDTO(event.getHack());
    this.resourceTeamReference = event.getTeam();
    this.message = event.getMessage();
  }

  public EventDTO() {}

  public UUID getId() {
    return id;
  }

  public UserDTO getSender() {
    return sender;
  }

  public UserDTO getReceiver() {
    return receiver;
  }

  public String getType() {
    return type;
  }

  public String getStatus() {
    return status;
  }

  public Date getDateOfCreation() {
    return dateOfCreation;
  }

  public Date getDateOfUpdate() {
    return dateOfUpdate;
  }

  public HackDTO getResourceHackReference() {
    return resourceHackReference;
  }

  public Team getResourceTeamReference() {
    return resourceTeamReference;
  }

  public String getMessage() {
    return message;
  }


}
