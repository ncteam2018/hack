package com.netcracker.hack.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.mapper.TagMapper;
import com.netcracker.hack.model.Hack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.model.Tag;
import com.netcracker.hack.repository.TagRepository;
import com.netcracker.hack.service.TagsService;

@Service
public class TagsServiceImpl implements TagsService {

  @Autowired
  private TagRepository repository;

  @Override
  public List<TagDTO> getAllTags() {
    return TagConverter.convertTo( (List<Tag>)repository.findAll() );
  }

}
