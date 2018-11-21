package com.netcracker.hack.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SortDTO {

  private String property;
  private String direction;

  @JsonCreator
  public SortDTO(@JsonProperty("property") String property,
      @JsonProperty("direction") String direction) {
    this.property = property;
    this.direction = direction;

    System.out.println(this.toString());
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  @Override
  public String toString() {
    return "SortDTO [property=" + property + ", direction=" + direction + "]";
  }
}
