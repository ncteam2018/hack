package com.netcracker.hack.service.Impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.converter.EventConverter;
import com.netcracker.hack.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.EventStatusRepository;
import com.netcracker.hack.repository.EventTypeRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.EventService;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository repository;

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private HackRepository hackRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private EventTypeRepository eventTypeRepository;

  @Autowired
  private EventStatusRepository eventStatusRepository;

  public void createEvent(Integer typeID, Integer statusID, UUID senderID, UUID receiverID,
      UUID hackID, UUID teamID, String message) {

    Event newEvent = new Event();


    newEvent.setSender(profileRepository.findById(senderID).get());
    newEvent.setReceiver(profileRepository.findById(receiverID).get());

    newEvent.setType(eventTypeRepository.findById(typeID).get());
    newEvent.setStatus(eventStatusRepository.findById(statusID).get());

    if (hackID != null)
      newEvent.setResourceHackReference(hackRepository.findById(hackID).get());

    if (teamID != null)
      newEvent.setResourceTeamReference(teamRepository.findById(teamID).get());

    newEvent.setMessage(message);

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Date date = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());

    newEvent.setDateOfCreation(date);
    newEvent.setDateOfUpdate(date);

    repository.save(newEvent);
  }

  public List<List<EventDTO>> getEvents(Integer typeID, UUID ownerID) {

    List<List<EventDTO>> userEvents = new ArrayList<>();

    userEvents.add(EventConverter.convertTo(repository.findByTypeIdAndSenderUuid(typeID, ownerID)));
    userEvents
        .add(EventConverter.convertTo(repository.findByTypeIdAndReceiverUuid(typeID, ownerID)));
    userEvents.add(EventConverter.convertTo(repository.findByTypeIdAndSenderUuid(2,
        UUID.fromString("00000000-0000-0000-0000-000000000000"))));

    return userEvents;
  }

  public void updateEventStatus(Integer eventID, Integer newStatusID) {

    Event updatedEvent = repository.findById(eventID).get();
    updatedEvent.setStatus(eventStatusRepository.findById(newStatusID).get());

    repository.save(updatedEvent);
  }

  public Long countNewEvents(Integer newStatusID, UUID ownerID) {

    Long a = repository.countByReceiverUuidAndStatusIdNot(ownerID, newStatusID);
    return a;
  }

}
