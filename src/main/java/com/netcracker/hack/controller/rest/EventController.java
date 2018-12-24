package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.dto.NotificationDTO;
import com.netcracker.hack.model.Event;
import com.netcracker.hack.service.EventService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/event")
public class EventController {

  @Autowired
  private EventService service;

  @ApiOperation("Returns all events for user")
  @GetMapping
  public List<List<EventDTO>> getUserEvents(
      @RequestParam(name = "eventType", required = true) Integer typeID,
      @RequestParam(name = "ownerID", required = true) UUID ownerID) {

    return service.getEvents(typeID, ownerID);
  }

  @GetMapping("/{userID}/myEvents")
  public List<List<Event>> getAllEvents(@PathVariable UUID userID) {
    return service.getAllUserEvents(userID);
  }
  
  @GetMapping("/notifications")
  public List<NotificationDTO> getUserNotifications(
      @RequestParam(name = "ownerID", required = true) UUID ownerID) {
    return service.getUserNotifications(ownerID);
  }

  @PutMapping("/notifications")
  public void updateUserNotification(@RequestBody NotificationDTO notification) {
    service.updateUserNotifications(notification);
  }

  @ApiOperation("Update event status")
  @PutMapping("/{eventID}")
  public void updateEventStatus(@PathVariable UUID eventID, @RequestBody Integer newStatus) {

    service.updateEventStatus(eventID, newStatus);
  }

  @ApiOperation("Delete event")
  @DeleteMapping("/{eventID}")
  public void deleteEvent(@PathVariable UUID eventID) {

    service.deleteEvent(eventID);
  }

  @ApiOperation("Returns count of new events")
  @GetMapping("/count")
  public Long getNewEventCounter(@RequestParam(name = "ownerID", required = true) UUID ownerID) {

    return service.countNewEvents(2, ownerID);
  }


  @ApiOperation("Creates notification for user")
  @PostMapping("/sendNotification")
  public void sendNotificationToUser(@RequestParam String message,
      @RequestParam(required = false) UUID hackID, @RequestParam(required = false) UUID teamID,
      @RequestParam(required = false) UUID userID, @RequestParam UUID receiver) {
    service.sendNotificationToUser(message, hackID, teamID, userID, receiver);
  }

  @ApiOperation("Get Event by id")
  @GetMapping("/{id}")
  public EventDTO getEventById(@PathVariable UUID id) {
    return service.getEventById(id);
  }
}
