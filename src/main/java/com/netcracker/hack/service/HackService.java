package com.netcracker.hack.service;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Hack;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface HackService {

  Page<HackDTO> getFilteredHacks(PageRequestBuilder requestBuilder);

  HackDTO getHack(UUID id);

  List<Hack> getHackByCompany(UUID id);

  void deleteHack(UUID id);

  ResponseEntity<Object> createHack(HackDTO hack, String creatorName);

  ResponseEntity<Object> updateHack(HackDTO hackDTO, UUID id);
}
