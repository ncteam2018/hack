package com.netcracker.hack.dto.converter;

import com.netcracker.hack.dto.EventDTO;
import com.netcracker.hack.model.Event;
import java.util.ArrayList;
import java.util.List;

public class EventConverter {
  public static List<EventDTO> convertTo(List<Event> events) {

    List<EventDTO> eventDTOList = new ArrayList<>();

    for (Event event : events)
      eventDTOList.add(new EventDTO(event));// TagMapper.INSTANCE.tagToTagDTO(tag));

    return eventDTOList;
  }
}
