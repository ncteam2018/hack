package com.netcracker.hack.repository;

import com.netcracker.hack.model.Tag;
import com.netcracker.hack.model.Team;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, UUID> {

  public Team findByUuid(UUID uuid);
  public Page<Team> findDistinctByHackNameContainsAndHackPlaceContains(Pageable pageable,
      String hackName, String cityName);

  public Page<Team> findDistinctBySkillTagsInAndScopeTagsInAndHackNameContainsAndHackPlaceContains(
      Pageable pageable, List<Tag> skillTags, List<Tag> scopeTags, String hackName,
      String cityName);

  public Page<Team> findDistinctByScopeTagsInAndHackNameContainsAndHackPlaceContains(
      Pageable pageable, List<Tag> scopeTags, String hackName, String cityName);

  public Page<Team> findDistinctBySkillTagsInAndHackNameContainsAndHackPlaceContains(
      Pageable pageable, List<Tag> skillTags, String hackName, String cityName);
}
