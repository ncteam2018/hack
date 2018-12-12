package com.netcracker.hack.dto;

import com.netcracker.hack.model.Event;

public class NotificationDTO {

  private Integer id;
  private String status;
  private HackDTO resourceHackReference;
  private String message;

  public NotificationDTO(Event event) {
    this.id = event.getId();
    this.status = event.getStatus().getStatus();
    //this.resourceHackReference = new HackDTO(event.getHack());
    this.message = event.getMessage();
  }

  public NotificationDTO() {}

  public Integer getId() {
    return id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public HackDTO getResourceHackReference() {
    return resourceHackReference;
  }

  public void setResourceHackReference(HackDTO resourceHackReference) {
    this.resourceHackReference = resourceHackReference;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
