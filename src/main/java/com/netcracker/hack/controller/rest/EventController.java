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

  @ApiOperation("Returns all events")
  @GetMapping("/all")
  public List<Event> getAllEvents() {
    return service.getAllEvents();
  }

  @ApiOperation("Returns all user notifications")
  @GetMapping("/notifications")
  public List<NotificationDTO> getUserNotifications(
      @RequestParam(name = "ownerID", required = true) UUID ownerID) {
    return service.getUserNotifications(ownerID);
  }

  @ApiOperation("Returns all user notifications")
  @PutMapping("/notifications")
  public void updateUserNotification(@RequestBody NotificationDTO notification) {
    service.updateUserNotifications(notification);
  }

  @ApiOperation("Update event status")
  @PutMapping("/{id}")
  public void updateEventStatus(@PathVariable Integer id, @RequestBody Integer newStatus) {

    service.updateEventStatus(id, newStatus);
  }

  @ApiOperation("Returns count of new events")
  @GetMapping("/count")
  public Long getNewEventCounter(@RequestParam(name = "ownerID", required = true) UUID ownerID) {

    return service.countNewEvents(2, ownerID);
  }

  @ApiOperation("Creats notification")
  @PostMapping("/notifications")
  public void createHackNotification( @RequestParam(name = "hackID", required = true) UUID hackID,
                                      @RequestParam(name = "message", required = true) String message){
    service.createHackNotifications(hackID, message);
  }
}
