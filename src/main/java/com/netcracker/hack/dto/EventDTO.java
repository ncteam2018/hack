package com.netcracker.hack.dto;

import java.sql.Date;
import com.netcracker.hack.model.Event;
import com.netcracker.hack.model.Team;

public class EventDTO {

  private Integer id;
  private UserDTO sender;
  private UserDTO receiver;
  private String type;
  private String status;
  private Date dateOfCreation;
  private Date dateOfUpdate;
  private HackDTO resourceHackReference;
  private Team resourceTeamReference; // TODO: Team -> TeamDTO
  private String message;

  public EventDTO(Event event) {
    this.id = event.getId();
    this.sender = new UserDTO(event.getSender());
    this.receiver = new UserDTO(event.getReceiver());
    this.type = event.getType().getType();
    this.status = event.getStatus().getStatus();
    this.dateOfCreation = event.getDateOfCreation();
    this.dateOfUpdate = event.getDateOfUpdate();
    this.resourceHackReference = new HackDTO(event.getResourceHackReference());
    this.resourceTeamReference = event.getResourceTeamReference();
    this.message = event.getMessage();
  }

  public EventDTO() {}

  public Integer getId() {
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
