package com.netcracker.hack.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilterDTO  {

  private String property;
  private String value;

  @JsonCreator
  public FilterDTO(@JsonProperty("property") String property,@JsonProperty("value") String value) {
    this.property = property;
    this.value = value;
    
    System.out.println(this.toString());
  }

  public FilterDTO() {}

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "FilterDTO [property=" + property + ", value=" + value + "]";
  }
}
