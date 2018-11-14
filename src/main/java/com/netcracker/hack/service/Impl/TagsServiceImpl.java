package com.netcracker.hack.service.Impl;

import java.util.List;
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
   
    return TagDTO.convertTo( (List<Tag>)repository.findAll() );
  }

}
