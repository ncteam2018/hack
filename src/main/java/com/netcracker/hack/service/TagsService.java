package com.netcracker.hack.service;

import java.util.List;
import com.netcracker.hack.dto.TagDTO;

public interface TagsService {
  public List<List<TagDTO>> getAllTags();
  public List<TagDTO> verifyTags(List<TagDTO> untrustedTags);
}
