package com.netcracker.hack.service;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.model.Hack;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface HackService {

  Page<HackDTO> getAllHack(Pageable pageRequest, List<TagDTO> tags, String hackName);

  HackDTO getHack(UUID id);

  List<Hack> getHackByCompany(UUID id);

  void deleteHack(UUID id);

  ResponseEntity<Object> createHack(HackDTO hack);

  ResponseEntity<Object> updateHack(Hack hack, UUID id);
}
