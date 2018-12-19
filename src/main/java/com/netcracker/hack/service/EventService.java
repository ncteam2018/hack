package com.netcracker.hack.service;

import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.NotificationDTO;
import com.netcracker.hack.model.Event;

public interface EventService {

  public static final int CREATE_HACK_EVENT_TYPE = 1;
  public static final int NOTIFICATION_EVENT_TYPE = 2;
  public static final int INVITE_EVENT_TYPE = 3;
  public static final int MESSAGE_EVENT_TYPE = 4;

  public static final int OK_EVENT_STATUS = 1;
  public static final int PROCESSING_EVENT_STATUS = 2;
  public static final int CANCELED_EVENT_STATUS = 3;

  public void sendToAdmin(Integer typeID, Integer statusID, UUID senderID, UUID hackID, UUID teamID,
      String message);

  public void sendToUser(Integer typeID, Integer statusID, UUID senderID, UUID receiverID,
      UUID hackID, UUID teamID, String message);

  public List<List<EventDTO>> getEvents(Integer typeID, UUID ownerID);

  public List<Event> getAllEvents();

  public void updateEventStatus(UUID eventID, Integer newStatusID);
  public EventDTO getEventById(UUID id);

  public void updateEventStatus(Integer eventID, Integer newStatusID);

  public Long countNewEvents(Integer newStatusID, UUID ownerID);

  public List<NotificationDTO> getUserNotifications(UUID ownerID);

  public void updateUserNotifications(NotificationDTO notification);

  public void createNotification(String message, UUID hackID, UUID teamID, UUID userID);

  public void sendNotificationToUser(String message, UUID hackID, UUID teamID, UUID receiver);


}
