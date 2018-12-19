package com.netcracker.hack.repository;

import com.netcracker.hack.model.Event;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
  
  public List<Event> findByTypeIdAndReceiverUuid(Integer typeId, UUID profileUuid );
  public List<Event> findByTypeIdAndSenderUuid(Integer typeId, UUID profileUuid);
  public List<Event>  findByHackUuid(UUID profileUuid);
  
  public Event  findByHackUuidAndTypeId(UUID hackID, Integer eventTypeID);
  public Event  findById(UUID eventUuid);

  public Long countByReceiverUuidAndTypeIdNot(UUID profileUuid, Integer typeID);
  public List<Event> findByTypeIdAndReceiverUuidAndStatusId(Integer typeId, UUID profileUuid, Integer statusId );

  public List<Event> findByTypeIdAndStatusStatus(Integer typeID, String status);
  public List<Event> findByTypeIdAndDateOfCreation(Integer typeID, Date removeDate);
}
