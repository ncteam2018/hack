package com.netcracker.hack.service.Impl;

import java.util.ArrayList;
import java.util.List;
import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.TagRepository;
import com.netcracker.hack.service.TagsService;

@Service
public class TagsServiceImpl implements TagsService {

  @Autowired
  private HackRepository hackRepository;
  
  @Autowired
  private TagRepository tagRepository;

  @Override
  public List<List<TagDTO>> getAllTags() {

    List<List<TagDTO>> allTags = new ArrayList<>();

    allTags.add(TagConverter.convertTo(hackRepository.findDistinctSkillTagsAndStatus("Active")));
    allTags.add(TagConverter.convertTo(hackRepository.findDistinctScopeTagsAndStatus("Active")));

    return allTags;
  }
  
  @Override
  public List<TagDTO> verifyTags(List<TagDTO> untrustedTags) {

    List<Tag> verifiedTags = TagConverter.convertFrom(untrustedTags);
    for (Tag tag : verifiedTags) {
      Tag existingTag = tagRepository.findByTag(tag.getTag());
      if (existingTag == null) {
        tag.setId(null);
        tagRepository.save(tag);
      } else
        tag.setId(existingTag.getId());

    }

    return TagConverter.convertTo(verifiedTags);
  }
}
