package com.netcracker.hack.service;

import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;

public interface EventService {

  public void createEvent(Integer typeID, Integer statusID, UUID sender_id, UUID receiver_id,
      UUID hack_id, UUID team_id, String message);

  public void createHackNotifications(UUID hackID, String message);

  public List<List<EventDTO>> getEvents(Integer typeID, UUID ownerID);

  public void updateEventStatus(Integer eventID, Integer newStatusID);

  public Long countNewEvents(Integer newStatusID, UUID ownerID);

}
