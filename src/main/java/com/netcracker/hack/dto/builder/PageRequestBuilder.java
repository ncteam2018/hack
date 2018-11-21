package com.netcracker.hack.dto.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.hack.dto.FilterDTO;
import com.netcracker.hack.dto.SortDTO;
import com.netcracker.hack.model.Tag;

public class PageRequestBuilder {

  private List<Tag> skillTags;
  private List<Tag> scopeTags;
  private PageRequest pageRequest;
  private String searchName;
  private String searchCompanyName;
  private boolean isFiltered;

  public PageRequestBuilder(String sortJson, int page, int size, String filtersJson) {

    SortDTO sort = null;
    FilterDTO[] filters = null;

    try {
      ObjectMapper mapper = new ObjectMapper();
      if (sortJson != null)
        sort = mapper.readValue(sortJson, SortDTO.class);
      if (filtersJson != null)
        filters = mapper.readValue(filtersJson, FilterDTO[].class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (sort == null)
      sort = new SortDTO("name", "ASC");

    pageRequest = PageRequest.of(page, size,
        Sort.by(Sort.Direction.fromString(sort.getDirection()), sort.getProperty()));
    skillTags = new ArrayList<>();
    scopeTags = new ArrayList<>();
    searchName = "";
    searchCompanyName = "";
    isFiltered = false;

    if (filters != null)
      for (FilterDTO filter : filters) {

        switch (filter.getProperty()) {
          case "name":
            searchName = filter.getValue();
            break;
          case "companyName":
            searchCompanyName = filter.getValue();
            break;
          case "skill":
            isFiltered = true;
            skillTags.add(new Tag(Integer.parseInt(filter.getValue()), ""));
            break;
          case "scope":
            isFiltered = true;
            scopeTags.add(new Tag(Integer.parseInt(filter.getValue()), ""));
            break;
        };
      }
  }

  public List<Tag> getSkillTags() {
    return skillTags;
  }

  public List<Tag> getScopeTags() {
    return scopeTags;
  }

  public String getSearchName() {
    return searchName;
  }

  public PageRequest getPageRequest() {
    return pageRequest;
  }

  public boolean isFiltered() {
    return isFiltered;
  }

  public String getSearchCompanyName() {
    return searchCompanyName;
  }

}
