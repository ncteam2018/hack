package com.netcracker.hack.dto;

import java.util.ArrayList;
import java.util.List;
import com.netcracker.hack.model.Tag;

public class TagDTO {

  private Integer id;
  private String tagName;
  private String category;

  public TagDTO(String id) {
    this.id = Integer.parseInt(id);
    this.tagName="";
    this.category = "";}

  public TagDTO(Integer id, String tag, String type) {
    this.id = id;
    this.tagName = tag;
    this.category = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

//  public static List<TagDTO> convertTo(List<Tag> tags) {
//
//    List<TagDTO> tagDTOList = new ArrayList<>();
//
//    for (Tag tag : tags)
//      tagDTOList.add(new TagDTO(tag.getId(), tag.getTag(), tag.getType()));
//
//    return tagDTOList;
//  }
//
//  public static List<Tag> convertFrom(List<TagDTO> tags) {
//
//    List<Tag> tagList = new ArrayList<>();
//
//    for (TagDTO tag : tags)
//      tagList.add(new Tag(tag.getId(), tag.getTagName(), tag.getCategory()));
//
//    return tagList;
//  }
//
//  public static Tag[] convertFromM(TagDTO[] tags) {
//
//   Tag[] tagList = new Tag[tags.length];
//
//   int i=0;
//    for (TagDTO tag : tags) {
//      tagList[i] = new Tag(tag.getId(), tag.getTagName(), tag.getCategory());
//      i++;
//    }
//    return tagList;
//  }

  public TagDTO() {
  }
}
