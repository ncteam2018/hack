package com.netcracker.hack.repository;

import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HackRepository extends PagingAndSortingRepository<Hack, UUID> {

  // public List<Hack> findByCompany_Uuid(UUID uuid);
  
  public Page<Hack> findAllByNameContains(Pageable pageable, String hackName);
  public Page<Hack> findDistinctByTagsInAndNameContains(Pageable pageable,List<Tag> tags,String hackName);
}
