package com.netcracker.hack.repository;

import com.netcracker.hack.model.CompanyData;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyData, UUID> {
  

  @Query("SELECT DISTINCT companyName FROM CompanyData ")
  public Set<String> findDistinctCompNames();
}
