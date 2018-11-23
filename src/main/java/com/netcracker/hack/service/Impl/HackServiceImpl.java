package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.TagDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.dto.converter.TagConverter;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Profile;
import com.netcracker.hack.model.Tag;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.ProfileRepository;
import com.netcracker.hack.repository.TagRepository;
import com.netcracker.hack.service.HackService;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class HackServiceImpl implements HackService {

  @Autowired
  private HackRepository hackRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private ProfileRepository profileRepository;

  public Page<HackDTO> getFilteredHacks(PageRequestBuilder requestBuilder) {
    Page<Hack> tagPage;

    if (requestBuilder.isFiltered()) {
      if (requestBuilder.getSkillTags().size() > 0 && requestBuilder.getScopeTags().size() > 0)
        tagPage = hackRepository
            .findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getScopeTags(), requestBuilder.getSearchName(),
                requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName());
      else if (requestBuilder.getSkillTags().size() > 0)
        tagPage =
            hackRepository.findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName());
      else
        tagPage =
            hackRepository.findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
                requestBuilder.getPageRequest(), requestBuilder.getScopeTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName());
    } else
      tagPage = hackRepository.findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContains(
          requestBuilder.getPageRequest(), requestBuilder.getSearchName(),
          requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName());

    PageImpl<HackDTO> tagDTOPage = new PageImpl<HackDTO>(makeListOfHackDTO(tagPage.getContent()),
        tagPage.getPageable(), tagPage.getTotalElements());

    return tagDTOPage;
  }

  public HackDTO getHack(UUID id) {
    Optional<Hack> hack = hackRepository.findById(id);
    if (!hack.isPresent()) {
      return new HackDTO();
    }


    // return new HackDTO(hack.get());
    return null; // HackMapper.INSTANCE.hackToHackDTO( hack.get() );
  }

  public List<Hack> getHackByCompany(UUID id) {
    return null;// repository.findByCompany_Uuid(id);
  }

  public void deleteHack(UUID id) {
    hackRepository.deleteById(id);
  }

  public ResponseEntity<Object> createHack(HackDTO hackDTO, String creatorName) {

    hackDTO.setSkillTags(verifyTags(hackDTO.getSkillTags()));
    hackDTO.setScopeTags(verifyTags(hackDTO.getScopeTags()));

    Profile companyProfile = profileRepository.findByLogin(creatorName);
    hackDTO.setCompany(companyProfile.getCompanyProfile());

    Hack savedHack = hackRepository.save(new Hack(hackDTO));
    // Hack savedHack = hackRepository.save(HackMapper.INSTANCE.hackDTOToHack(hackDTO));

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedHack.getUuid()).toUri();

    return ResponseEntity.created(location).build();
  }



  public ResponseEntity<Object> updateHack(Hack hack, UUID id) {
    Optional<Hack> hackOptional = hackRepository.findById(id);
    if (!hackOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    hack.setUuid(id);
    hackRepository.save(hack);
    return ResponseEntity.noContent().build();
  }

  private List<HackDTO> makeListOfHackDTO(List<Hack> hacks) {

    ArrayList<HackDTO> hackDTOList = new ArrayList<>();

    hacks.forEach((Hack hack) -> {
      hackDTOList.add(new HackDTO(hack));
      // hackDTOList.add(HackMapper.INSTANCE.hackToHackDTO(hack));
    });

    return hackDTOList;
  }

  private List<TagDTO> verifyTags(List<TagDTO> untrustedTags) {

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
