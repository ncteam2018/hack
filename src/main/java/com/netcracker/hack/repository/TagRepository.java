package com.netcracker.hack.repository;

import com.netcracker.hack.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
  
  public Tag findByTag(String tag);
}
