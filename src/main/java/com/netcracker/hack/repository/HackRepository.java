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

  public Page<Hack> findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
      Pageable pageable, String hackName, String companyName, String cityName, String status);

  public Page<Hack> findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
      Pageable pageable, List<Tag> skillTags, List<Tag> scopeTags, String hackName,
      String companyName, String cityName, String status);

  public Page<Hack> findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
      Pageable pageable, List<Tag> scopeTags, String hackName, String companyName, String cityName, String status);

  public Page<Hack> findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
      Pageable pageable, List<Tag> skillTags, String hackName, String companyName, String cityName, String status);


  @Query("SELECT DISTINCT skillTags FROM Hack")
  List<Tag> findDistinctSkillTags(String status);

  @Query("SELECT DISTINCT scopeTags FROM Hack")
  List<Tag> findDistinctScopeTags(String status);
}
