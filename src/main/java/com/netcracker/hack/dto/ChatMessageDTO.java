package com.netcracker.hack.dto;

import java.sql.Date;
import com.netcracker.hack.model.TeamChatMessage;

public class ChatMessageDTO {

  private Integer id;
  private String senderName;
  private Date date;
  private String message;

  public ChatMessageDTO(TeamChatMessage message) {
    this.id = message.getId();
    this.senderName = message.getSenderName();
    this.date = message.getDate();
    this.message = message.getMessage();
  }

  public ChatMessageDTO() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

}
