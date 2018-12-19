package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.service.HackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hack")
public class HackController {
  @Autowired
  private HackService service;

  @ApiOperation("Returns all hacks by filter")
  @GetMapping
  public Page<HackDTO> getFilteredHacks(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "size", defaultValue = "10", required = false) int size,
      @RequestParam(name = "sort", required = false) String sortJson,
      @RequestParam(name = "filter", required = false) String filtersJson) {

    return service.getFilteredHacks(new PageRequestBuilder(sortJson, page, size, filtersJson));
  }

  @ApiOperation("Returns all hacks' names")
  @GetMapping("/names")
  public List<Pair<Object, Object>> getAllHackNames() {

    return service.getAllHackNames();
  }

  @ApiOperation("Returns all hacks' places")
  @GetMapping("/places")
  public Set<String> getAllHackPlaces() {

    return service.getAllHackPlaces();
  }

  @ApiOperation("Returns all company names")
  @GetMapping("/companies")
  public Set<String> getAllCompNames() {

    return service.getAllCompNames();
  }

  @ApiOperation("Returns all hacks by company UUID")
  @GetMapping("company/{id}")
  public List<Hack> getHackByCompanyUuid(
      @ApiParam(value = "Company's uuid", required = true) @PathVariable UUID id) {
    return service.getHackByCompany(id);
  }

  @ApiOperation("Returns hack by uuid")
  @GetMapping("/{hackID}")
  public HackDTO getHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID hackID) {
    return service.getHack(hackID);
  }

  @ApiOperation("Deletes hack profile by uuid")
  @DeleteMapping("/{id}")
  public void deleteHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
    service.deleteHack(id);
  }

  @ApiOperation("Adds new hack")
  @PostMapping
  public ResponseEntity<Object> createHack(
      @ApiParam(value = "new Hack") @RequestBody HackDTO hackDTO, Principal principal) {
    return service.createHack(hackDTO, principal.getName());
  }

  @ApiOperation("Updates hack by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateHack(
      @ApiParam(value = "new Hack", required = true) @RequestBody HackDTO hackDTO,
      @ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
    return service.updateHack(hackDTO, id);
  }

  @ApiOperation("Updates hack status by uuid")
  @PutMapping("/{id}/status")
  public ResponseEntity<Object> updateStatus(@PathVariable UUID id, @RequestBody String status){
    return service.updateStatus(id, status);
  }
}
