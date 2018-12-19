package com.netcracker.hack.model;

import java.sql.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

  @Id
  @Column(name = "id")
  private UUID id;

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
  private Hack hack;

  @ManyToOne
  private Team team;

  @Column(name = "message", length = 1000)
  private String message;

  public Event() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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

  public Hack getHack() {
    return hack;
  }

  public void setHack(Hack resourceHackReference) {
    this.hack = resourceHackReference;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team resourceTeamReference) {
    this.team = resourceTeamReference;
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
        + dateOfUpdate + ", resourceHackReference=" + hack
        + ", resourceTeamReference=" + team + ", message=" + message + "]";
  }
}
