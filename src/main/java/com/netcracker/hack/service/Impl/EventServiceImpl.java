package com.netcracker.hack.service.Impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.converter.EventConverter;
import com.netcracker.hack.model.Event;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.EventStatusRepository;
import com.netcracker.hack.repository.EventTypeRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.repository.SubscriptionRepository;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.EventService;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private HackRepository hackRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private SubscriptionRepository subRepository;

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

    eventRepository.save(newEvent);
  }

  public void createHackNotifications(UUID hackID, String message) {

    Hack resourceHack = hackRepository.findById(hackID).get();
    String hackCity = resourceHack.getPlace().split(",")[1].trim();

    List<Subscription> subscriptions = subRepository.findByCityName(hackCity);


    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Date currentDate = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());

    List<Profile> notifiedUsers = new ArrayList<>();
    List<Event> newNotifications = new ArrayList<>();

    for (Subscription sub : subscriptions) {

      Profile user = sub.getUser();

      if (notifiedUsers.contains(user))
        continue;

      notifiedUsers.add(user);

      Event notification = new Event();
      notification.setReceiver(user);
      notification.setStatus(eventStatusRepository.findById(1).get());
      notification.setDateOfCreation(currentDate);
      notification.setDateOfUpdate(currentDate);
      notification.setResourceHackReference(resourceHack);
      notification.setMessage(message);

      newNotifications.add(notification);
    }

    eventRepository.saveAll(newNotifications);
  }

  public List<List<EventDTO>> getEvents(Integer typeID, UUID ownerID) {

    List<List<EventDTO>> userEvents = new ArrayList<>();

    userEvents
        .add(EventConverter.convertTo(eventRepository.findByTypeIdAndSenderUuid(typeID, ownerID)));
    userEvents.add(
        EventConverter.convertTo(eventRepository.findByTypeIdAndReceiverUuid(typeID, ownerID)));

    return userEvents;
  }

  public void updateEventStatus(Integer eventID, Integer newStatusID) {

    Event updatedEvent = eventRepository.findById(eventID).get();
    updatedEvent.setStatus(eventStatusRepository.findById(newStatusID).get());

    eventRepository.save(updatedEvent);
  }

  public Long countNewEvents(Integer newStatusID, UUID ownerID) {

    Long a = eventRepository.countByReceiverUuidAndStatusIdNot(ownerID, newStatusID);
    return a;
  }

  public List<Event> getAllEvents(){
    return (List<Event>) eventRepository.findAll();
  }
}
