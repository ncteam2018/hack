package com.netcracker.hack.controller.rest;

import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.service.TagsService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class TagsController {

  @Autowired
  private TagsService service;

  @ApiOperation("Returns all tags")
  @GetMapping
  public List<TagDTO> getAllTags() {
    return service.getAllTags();
  }

}
