package com.netcracker.hack.service;

import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.NotificationDTO;
import com.netcracker.hack.model.Event;

public interface EventService {

  public void createEvent(Integer typeID, Integer statusID, UUID sender_id, UUID receiver_id,
      UUID hack_id, UUID team_id, String message);

  public void createHackNotifications(UUID hackID, String message);

  public List<List<EventDTO>> getEvents(Integer typeID, UUID ownerID);

  public List<Event> getAllEvents();

  public EventDTO getEventById(Integer id);

  public void updateEventStatus(Integer eventID, Integer newStatusID);

  public Long countNewEvents(Integer newStatusID, UUID ownerID);

  public List<NotificationDTO> getUserNotifications(UUID ownerID);
  
  public void updateUserNotifications(NotificationDTO notification);

  public void sendNotificationToUser(String message, UUID hackID, UUID teamID, UUID receiver);
  
  
}
