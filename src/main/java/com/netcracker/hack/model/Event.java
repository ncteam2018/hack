package com.netcracker.hack.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  private Profile sender;

  @ManyToOne
  private Profile receiver;

  @ManyToOne
  private EventType type;

  @ManyToOne
  private EventStatus status;

  @Column(name = "dateOfCreation")
  private Date dateOfCreation;

  @Column(name = "dateOfUpdate")
  private Date dateOfUpdate;

  @ManyToOne
  private Hack resourceHackReference;

  @ManyToOne
  private Team resourceTeamReference;

  @Column(name = "message")
  private String message;

  public Event() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Profile getSender() {
    return sender;
  }

  public void setSender(Profile sender) {
    this.sender = sender;
  }

  public Profile getReceiver() {
    return receiver;
  }

  public void setReceiver(Profile receiver) {
    this.receiver = receiver;
  }

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  public Date getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(Date dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public Date getDateOfUpdate() {
    return dateOfUpdate;
  }

  public void setDateOfUpdate(Date dateOfUpdate) {
    this.dateOfUpdate = dateOfUpdate;
  }

  public Hack getResourceHackReference() {
    return resourceHackReference;
  }

  public void setResourceHackReference(Hack resourceHackReference) {
    this.resourceHackReference = resourceHackReference;
  }

  public Team getResourceTeamReference() {
    return resourceTeamReference;
  }

  public void setResourceTeamReference(Team resourceTeamReference) {
    this.resourceTeamReference = resourceTeamReference;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Event other = (Event) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Event [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", type=" + type
        + ", status=" + status + ", dateOfCreation=" + dateOfCreation + ", dateOfUpdate="
        + dateOfUpdate + ", resourceHackReference=" + resourceHackReference
        + ", resourceTeamReference=" + resourceTeamReference + ", message=" + message + "]";
  }
}
