package com.netcracker.hack.repository;

import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Tag;
import java.util.List;
import java.util.UUID;
import javax.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HackRepository extends PagingAndSortingRepository<Hack, UUID> {

  public Hack findByUuid(UUID id);

  public Page<Hack> findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
      Pageable pageable, String hackName, String companyName, String cityName, String status);

  public Page<Hack> findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
      Pageable pageable, List<Tag> skillTags, List<Tag> scopeTags, String hackName,
      String companyName, String cityName, String status);

  public Page<Hack> findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
      Pageable pageable, List<Tag> scopeTags, String hackName, String companyName, String cityName,
      String status);

  public Page<Hack> findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
      Pageable pageable, List<Tag> skillTags, String hackName, String companyName, String cityName,
      String status);

  @Query("SELECT DISTINCT skillTags FROM Hack")
  public List<Tag> findDistinctSkillTags();

  @Query("SELECT DISTINCT scopeTags FROM Hack")
  public List<Tag> findDistinctScopeTags();

  @Query("SELECT name,uuid FROM Hack ")
  public List<Tuple> findAllName();
  
  @Query("SELECT place FROM Hack ")
  public List<String> findAllCities();
}
