package com.netcracker.hack.jobs;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Team;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.EventService;

@Component
public class NotificationScheduler {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private HackRepository hackRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private EventService eventService;

  @Scheduled(fixedDelay = 1000*60*60*24)
  public void clearNotificationJob() {

    eventRepository.deleteAll(eventRepository.findByTypeIdAndStatusStatus(2, "Canceled"));
    
    Date prevWeek = new Date(Calendar.getInstance().getTimeInMillis() - 7 * 24 * 60 * 60 * 1000);
    eventRepository.deleteAll(eventRepository.findByTypeIdAndDateOfCreation(2, prevWeek));

  }

  @Scheduled(fixedDelay = 1000*60*60*24)
  public void sendStartHackNotificationJob() {

    Date today = new Date(Calendar.getInstance().getTimeInMillis());
    Date nextWeek = new Date(Calendar.getInstance().getTimeInMillis() + 7 * 24 * 60 * 60 * 1000);

    List<Hack> hotHacks = hackRepository.findByStartDateBetween(today, nextWeek);

    hotHacks.forEach((Hack hotHack) -> {

      long diff = hotHack.getStartDate().getTime() - today.getTime();
      int dayDiff = (int) (diff / (1000 * 60 * 60 * 24));

      eventService.createNotification("Хакатон стартует через " + dayDiff + " дней!",
          hotHack.getUuid(), null, null);

      teamRepository.findByHackUuid(hotHack.getUuid()).forEach((Team team) -> {

        eventService.createNotification("Хакатон стартует через " + dayDiff + " дней!",
            hotHack.getUuid(), team.getUuid(), null);
      });

    });

  }

}
