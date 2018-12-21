package com.netcracker.hack.dto.builder;

import java.util.UUID;

public class NotificationBuilder {

  private StringBuilder notificationMessage =
      new StringBuilder(" <i class=\"fas fa-envelope\"></i> ");

  public NotificationBuilder() {}

  public NotificationBuilder addText(String text) {
    notificationMessage.append(text);
    return this;
  }

  public NotificationBuilder addHack(UUID hackID) {
    notificationMessage.append(" <a href=\"");
    notificationMessage.append("/hackPage/" + hackID);
    notificationMessage.append("\" class=\"btn-sm btn-dark\">Хакатон</a> ");

    return this;
  }

  public NotificationBuilder addTeam(UUID teamID) {
    notificationMessage.append(" <a href=\"");
    notificationMessage.append("/teamProfile/" + teamID);
    notificationMessage.append("\" class=\"btn-sm btn-dark\">Команда</a> ");

    return this;
  }

  public NotificationBuilder addUser(UUID userID) {
    notificationMessage.append(" <a href=\"");
    notificationMessage.append("/profileView/" + userID);
    notificationMessage.append("\" class=\"btn-sm btn-dark\">Пользователь</a> ");

    return this;
  }

  public String build(UUID notificationID) {
    
    StringBuilder notifStringCopy = new StringBuilder(notificationMessage);
    
    notifStringCopy.append(" <button onclick=\"");
    notifStringCopy.append("updateNotification(\'" + notificationID + "\') ");
    notifStringCopy.append("\" class=\"btn-sm btn-danger float-right\">X</button> ");
    
    return notifStringCopy.toString();
  }
}


