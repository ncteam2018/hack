package com.netcracker.hack.model;

import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.netcracker.hack.dto.ChatMessageDTO;
 
@Entity
@Table(name = "TeamChatMessages")
public class TeamChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  
  @ManyToOne
  private Team team;
  
  @Column(name = "senderName")
  private String senderName;
  
  @Column(name = "createDate")
  private Date date;
  
  @Column(name = "message", length = 1000)
  private String message;


  public TeamChatMessage() {
  }

  public TeamChatMessage(ChatMessageDTO message, Team team) {
    this.team = team;
    this.senderName = message.getSenderName();
    this.date = message.getDate();
    this.message = message.getMessage();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamChatMessage message = (TeamChatMessage) o;
    return Objects.equals(this.id, message.id) &&
        Objects.equals(this.team.getUuid(), message.team.getUuid()) ;
  }
}
