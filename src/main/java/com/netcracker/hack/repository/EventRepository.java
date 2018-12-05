package com.netcracker.hack.repository;

import com.netcracker.hack.model.Event;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Integer> {
  
  public List<Event> findByTypeIdAndReceiverUuid(Integer typeId, UUID profileUuid );
  public List<Event> findByTypeIdAndSenderUuid(Integer typeId, UUID profileUuid);
  public List<Event>  findByHackUuid(UUID profileUuid);
  
  public Long countByReceiverUuidAndTypeIdNot(UUID profileUuid, Integer typeID);
  public List<Event> findByTypeIdAndReceiverUuidAndStatusId(Integer typeId, UUID profileUuid, Integer statusId );
}
