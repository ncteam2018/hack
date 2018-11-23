package com.netcracker.hack.repository;

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

  public Page<Hack> findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
      Pageable pageable, String hackName, String companyName, String cityName);

  public Page<Hack> findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
      Pageable pageable, List<Tag> skillTags, List<Tag> scopeTags, String hackName,
      String companyName, String cityName);

  public Page<Hack> findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
      Pageable pageable, List<Tag> scopeTags, String hackName, String companyName, String cityName);

  public Page<Hack> findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
      Pageable pageable, List<Tag> skillTags, String hackName, String companyName, String cityName);


  @Query("SELECT DISTINCT skillTags FROM Hack")
  List<Tag> findDistinctSkillTags();

  @Query("SELECT DISTINCT scopeTags FROM Hack")
  List<Tag> findDistinctScopeTags();
}
