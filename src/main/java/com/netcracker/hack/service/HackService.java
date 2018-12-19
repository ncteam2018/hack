package com.netcracker.hack.service;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Hack;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

public interface HackService {

  public static final String ACTIVE_HACK_STATUS = "Active";
  public static final String Processing_HACK_STATUS = "Processing";
  public static final String CANCELED_HACK_STATUS = "Canceled";
  
  Page<HackDTO> getFilteredHacks(PageRequestBuilder requestBuilder);

  HackDTO getHack(UUID id);

  List<Hack> getHackByCompany(UUID id);

  void deleteHack(UUID id);

  ResponseEntity<Object> createHack(HackDTO hack, String creatorName);

  ResponseEntity<Object> updateHack(HackDTO hackDTO, UUID id);
  
  List<Pair<Object, Object>>  getAllHackNames();
  
  Set<String>  getAllHackPlaces();

  Set<String> getAllCompNames();

  ResponseEntity<Object> updateStatus(UUID id, String status);
}
