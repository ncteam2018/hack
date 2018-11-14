package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.service.Impl.HackServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
  private HackServiceImpl service;

  @ApiOperation("Returns all hacks")
  @GetMapping
  public Page<HackDTO> getAllHack(
      @RequestParam(name = "page", defaultValue = "0", required = false) int page,
      @RequestParam(name = "tags", required = false) TagDTO[] tags,
      @RequestParam(name = "direction", defaultValue = "ASC_name",
          required = false) String direction,
      @RequestParam(name = "hackName", defaultValue = "",
      required = false) String hackName, 
      @RequestParam(name = "size", defaultValue = "2", required = false) int size) {

    Sort sort = Sort.by(Sort.Direction.fromString(direction.split("_")[0]), direction.split("_")[1]);
    PageRequest pageRequest = PageRequest.of(page, size, sort);

    if (tags != null)
      return service.getAllHack(pageRequest, Arrays.asList(tags), hackName);
    else
      return service.getAllHack(pageRequest, null, hackName);

  }

  @ApiOperation("Returns all hacks by company UUID")
  @GetMapping("company/{id}")
  public List<Hack> getHackByCompanyUuid(
      @ApiParam(value = "Company's uuid", required = true) @PathVariable UUID id) {
    return service.getHackByCompany(id);
  }

  @ApiOperation("Returns hack by uuid")
  @GetMapping("/{id}")
  public HackDTO getHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
    return service.getHack(id);
  }

  @ApiOperation("Deletes hack profile by uuid")
  @DeleteMapping("/{id}")
  public void deleteHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
    service.deleteHack(id);
  }

  @ApiOperation("Adds new hack")
  @PostMapping
  public ResponseEntity<Object> createHack(
      @ApiParam(value = "new Hack") @RequestBody HackDTO hackDTO) {

    return service.createHack(hackDTO);
  }

  @ApiOperation("Updates hack by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateHack(
      @ApiParam(value = "new Hack", required = true) @RequestBody Hack hack,
      @ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
    return service.updateHack(hack, id);
  }
}
