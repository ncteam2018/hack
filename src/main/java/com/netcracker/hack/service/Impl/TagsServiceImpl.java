package com.netcracker.hack.service.Impl;

import java.util.ArrayList;
import java.util.List;
import com.netcracker.hack.dto.converter.TagConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.service.TagsService;

@Service
public class TagsServiceImpl implements TagsService {

  @Autowired
  private HackRepository repository;

  @Override
  public List<List<TagDTO>> getAllTags() {

    List<List<TagDTO>> allTags = new ArrayList<>();

    allTags.add(TagConverter.convertTo(repository.findDistinctSkillTags()));
    allTags.add(TagConverter.convertTo(repository.findDistinctScopeTags()));

    return allTags;
  }

}
