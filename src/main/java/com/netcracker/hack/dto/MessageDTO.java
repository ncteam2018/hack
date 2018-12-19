package com.netcracker.hack.dto;

import java.util.UUID;

public class MessageDTO {

  private UUID sender;
  private UUID receiver;
  private String message;
  
  public MessageDTO(UUID sender, UUID receiver, String message) {
    super();
    this.sender = sender;
    this.receiver = receiver;
    this.message = message;
  }
  
  public MessageDTO() {}

  public UUID getSender() {
    return sender;
  }

  public void setSender(UUID sender) {
    this.sender = sender;
  }

  public UUID getReceiver() {
    return receiver;
  }

  public void setReceiver(UUID receiver) {
    this.receiver = receiver;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "MessageDTO [sender=" + sender + ", receiver=" + receiver + ", message=" + message + "]";
  }
}
