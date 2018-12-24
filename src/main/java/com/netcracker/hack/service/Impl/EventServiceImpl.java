package com.netcracker.hack.service.Impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.NotificationDTO;
import com.netcracker.hack.dto.builder.NotificationBuilder;
import com.netcracker.hack.dto.converter.EventConverter;
import com.netcracker.hack.model.Event;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Subscription;
import com.netcracker.hack.model.UserAuthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.EventStatusRepository;
import com.netcracker.hack.repository.EventTypeRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.repository.SubscriptionRepository;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.repository.UserAuthRepository;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.RolesService;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private UserAuthRepository userAuthRepository;

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

  @Autowired
  private SimpMessagingTemplate template;


  private void createEvent(Integer typeID, Integer statusID, UUID senderID, UUID receiverID,
      UUID hackID, UUID teamID, String message) {

    Event newEvent = new Event();

    newEvent.setId(UUID.randomUUID());
    newEvent.setSender(profileRepository.findByUuid(senderID));
    newEvent.setReceiver(profileRepository.findByUuid(receiverID));

    newEvent.setType(eventTypeRepository.findById(typeID).get());
    newEvent.setStatus(eventStatusRepository.findById(statusID).get());

    if (hackID != null)
      newEvent.setHack(hackRepository.findById(hackID).get());

    if (teamID != null)
      newEvent.setTeam(teamRepository.findById(teamID).get());

    newEvent.setMessage(message);

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Date date = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());

    newEvent.setDateOfCreation(date);
    newEvent.setDateOfUpdate(date);

    template.convertAndSend("/topic/events/" + senderID, newEvent);
    template.convertAndSend("/topic/events/" + receiverID, newEvent);

    eventRepository.save(newEvent);
  }

  public void sendToAdmin(Integer typeID, Integer statusID, UUID senderID, UUID hackID, UUID teamID,
      String message) {

    userAuthRepository.findByRole(RolesService.ADMIN_ROLE).forEach((UserAuthData admin) -> {
      createEvent(typeID, statusID, senderID, admin.getUuid(), hackID, teamID, message);

      sendNotificationToUser("Пришёл новый запрос!", null, null, senderID, admin.getUuid());
    });
  }

  public void sendToUser(Integer typeID, Integer statusID, UUID senderID, UUID receiverID,
      UUID hackID, UUID teamID, String message) {

    createEvent(typeID, statusID, senderID, receiverID, hackID, teamID, message);
  }


  public void createNotification(String message, UUID hackID, UUID teamID, UUID userID) {

    NotificationBuilder builder = new NotificationBuilder();
    Set<Profile> receivers = new HashSet<>();
    int notificationType = 0;

    if (hackID != null)
      if (teamID == null)
        notificationType = 1;
      else
        notificationType = 2;
    else if (teamID != null)
      if (userID == null)
        notificationType = 3;
      else
        notificationType = 4;

    switch (notificationType) {
      case 1:
        builder.addText(message).addHack(hackID);

        Hack resourceHack = hackRepository.findById(hackID).get();
        String hackCity = resourceHack.getPlace().split(",")[1].trim();

        subRepository.findByCityName(hackCity).forEach((Subscription sub) -> {
          receivers.add(sub.getUser());
        });
        break;

      case 2:
        builder.addText(message).addHack(hackID).addTeam(teamID);

        receivers.add(teamRepository.findByUuid(teamID).getCaptain());
        break;

      case 3:
        builder.addText(message).addTeam(teamID);

        teamRepository.findByUuid(teamID).getTeamMembers().forEach((Profile member) -> {
          receivers.add(member);
        });
        break;

      case 4:
        builder.addText(message).addTeam(teamID).addUser(userID);

        teamRepository.findByUuid(teamID).getTeamMembers().forEach((Profile member) -> {
          receivers.add(member);
        });
        break;
      default:
        return;
    }

    List<Event> newNotifications = new ArrayList<>();
    for (Profile receiver : receivers) {
      Event notification = new Event();
      notification.setId(UUID.randomUUID());
      notification.setType(eventTypeRepository.findById(NOTIFICATION_EVENT_TYPE).get());
      notification.setReceiver(receiver);
      notification.setMessage(builder.build(notification.getId()));
      notification.setStatus(eventStatusRepository.findById(1).get());
      notification.setDateOfCreation(
          Date.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate()));
      newNotifications.add(notification);

      template.convertAndSend("/topic/notifications/" + receiver.getUuid(),
          new NotificationDTO(notification));
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

  public void updateEventStatus(UUID eventID, Integer newStatusID) {

    Event updatedEvent = eventRepository.findById(eventID);
    updatedEvent.setStatus(eventStatusRepository.findById(newStatusID).get());

    eventRepository.save(updatedEvent);
  }

  public Long countNewEvents(Integer newStatusID, UUID ownerID) {

    Long a = eventRepository.countByReceiverUuidAndTypeIdNot(ownerID, newStatusID);
    return a;
  }

  public List<Event> getAllEvents() {
    return (List<Event>) eventRepository.findAll();
  }

  public List<NotificationDTO> getUserNotifications(UUID ownerID) {

    List<Event> s = eventRepository.findByTypeIdAndReceiverUuidAndStatusId(2, ownerID, 1);

    return convertToNotificationDTO(s);
  }

  public void updateUserNotifications(NotificationDTO notification) {


  }

  private List<NotificationDTO> convertToNotificationDTO(List<Event> events) {

    ArrayList<NotificationDTO> notificationDTOList = new ArrayList<>();

    events.forEach((Event event) -> {
      notificationDTOList.add(new NotificationDTO(event));
    });

    return notificationDTOList;
  }

  @Override
  public void sendNotificationToUser(String message, UUID hackID, UUID teamID, UUID userID,
      UUID receiverID) {

    NotificationBuilder builder = new NotificationBuilder();

    if (hackID != null)
      builder.addText(message).addHack(hackID);
    else if (teamID != null)
      builder.addText(message).addTeam(teamID);
    else if (userID != null)
      builder.addText(message).addUser(userID);

    Event notification = new Event();
    notification.setId(UUID.randomUUID());
    notification.setType(eventTypeRepository.findById(NOTIFICATION_EVENT_TYPE).get());
    notification.setReceiver(profileRepository.findByUuid(receiverID));
    notification.setMessage(builder.build(notification.getId()));
    notification.setStatus(eventStatusRepository.findById(1).get());
    notification.setDateOfCreation(
        Date.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate()));

    eventRepository.save(notification);

    template.convertAndSend("/topic/notifications/" + receiverID,
        new NotificationDTO(notification));
  }

  public EventDTO getEventById(UUID id) {
    Event event = eventRepository.findById(id);
    return new EventDTO(event);
  }

  @Override
  public void updateEventStatus(Integer eventID, Integer newStatusID) {

  }

  @Override
  public List<List<Event>> getAllUserEvents(UUID userID) {

    List<List<Event>> allUserEvents = new ArrayList<List<Event>>();

    allUserEvents.add(
        eventRepository.findByTypeIdAndSenderUuid(EventService.CREATE_HACK_EVENT_TYPE, userID));

    allUserEvents.add(eventRepository.findByTypeIdAndReceiverUuidAndStatusId(
        EventService.CREATE_HACK_EVENT_TYPE, userID, EventService.PROCESSING_EVENT_STATUS));

    allUserEvents
        .add(eventRepository.findByTypeIdAndSenderUuid(EventService.INVITE_EVENT_TYPE, userID));


    allUserEvents.add(eventRepository.findByTypeIdAndReceiverUuidAndStatusId(
        EventService.INVITE_EVENT_TYPE, userID, EventService.PROCESSING_EVENT_STATUS));

    allUserEvents
        .add(eventRepository.findByTypeIdAndSenderUuid(EventService.MESSAGE_EVENT_TYPE, userID));
    allUserEvents
        .add(eventRepository.findByTypeIdAndReceiverUuid(EventService.MESSAGE_EVENT_TYPE, userID));


    // allUserEvents[ 1 ] - запрос на подтверждение моего хакатона (организация)
    // allUserEvents[ 2 ] - запрос на подтверждение хакатона (админ)

    // allUserEvents[ 3 ] - Приглашение в мою команду (капитан)
    // allUserEvents[ 4 ] - Запрос в команду (пользователь)

    // allUserEvents[ 5 ] - Мои отправленные сообщения
    // allUserEvents[ 6 ] - Мои входящие сообщения


    return allUserEvents;
  }

  @Transactional
  public void deleteEvent(UUID eventID) {
    eventRepository.deleteById(eventID);
  }
}
