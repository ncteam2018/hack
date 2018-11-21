package com.netcracker.hack.dto;

public class TagDTO {

  private Integer id;
  private String tagName;

  public TagDTO(String id) {
    this.id = Integer.parseInt(id);
    this.tagName = "";
  }

  public TagDTO(Integer id, String tag) {
    this.id = id;
    this.tagName = tag;
  }

  public TagDTO() {}

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
}
