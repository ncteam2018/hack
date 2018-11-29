package com.netcracker.hack.repository;

import com.netcracker.hack.model.Subscription;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
  
  public List<Subscription> findByCityName(String cityName );
}
