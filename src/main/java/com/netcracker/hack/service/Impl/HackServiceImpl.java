package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.HackService;
import com.netcracker.hack.service.ProfileService;
import com.netcracker.hack.service.TagsService;
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
  private ProfileService profileService;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TagsService tagService;

  @Autowired
  private EventService eventService;



  public Page<HackDTO> getFilteredHacks(PageRequestBuilder requestBuilder) {
    Page<Hack> tagPage;

    if (requestBuilder.isFiltered()) {
      if (requestBuilder.getSkillTags().size() > 0 && requestBuilder.getScopeTags().size() > 0)
        tagPage = hackRepository
            .findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getScopeTags(), requestBuilder.getSearchName(),
                requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName(),
                "Active");
      else if (requestBuilder.getSkillTags().size() > 0)
        tagPage = hackRepository
            .findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName(), "Active");
      else
        tagPage = hackRepository
            .findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
                requestBuilder.getPageRequest(), requestBuilder.getScopeTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName(), "Active");
    } else
      tagPage = hackRepository
          .findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatus(
              requestBuilder.getPageRequest(), requestBuilder.getSearchName(),
              requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName(), "Active");

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
    // eventService.updateEvent(eventID, statusID); -- Изменить статус уже удалённого хакатона
    hackRepository.deleteById(id);
  }

  public ResponseEntity<Object> createHack(HackDTO hackDTO, String creatorName) {

    hackDTO.setSkillTags(tagService.verifyTags(hackDTO.getSkillTags()));
    hackDTO.setScopeTags(tagService.verifyTags(hackDTO.getScopeTags()));

    UserDTO companyProfile = profileService.getProfileByLogin(creatorName);
    hackDTO.setCompany(companyProfile.getCompanyData());
    hackDTO.setStatus("Processing");
    Hack savedHack = hackRepository.save(new Hack(hackDTO));
    // Hack savedHack = hackRepository.save(HackMapper.INSTANCE.hackDTOToHack(hackDTO));

    eventService.createEvent(1, 2, companyProfile.getUuid(),
        UUID.fromString("6965c636-b957-44a7-b4fa-b6bd168bc760"), savedHack.getUuid(), null,
        "Подтвердите мой хакатон!"); // -- Эвент создания нового хакатона

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedHack.getUuid()).toUri();

    return ResponseEntity.created(location).build();
  }

  public ResponseEntity<Object> updateHack(HackDTO hackDTO, UUID id) {
    Optional<Hack> hackOptional = hackRepository.findById(id);
    if (!hackOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    hackDTO.setUuid(hackOptional.get().getUuid());

    String hackStatus = hackDTO.getStatus();
    if (hackStatus.equals("Active")) {
      hackRepository.save(new Hack(hackDTO));
      eventService.updateEventStatus(eventRepository.findByResourceHackReferenceUuid(id).getId(),
          1);
      eventService.createEvent(2, 1, UUID.fromString("00000000-0000-0000-0000-000000000000"),
          UUID.fromString("00000000-0000-0000-0000-000000000000"), id, null,
          "Объявлен новый хакатон, спешите принять участие!"); // -- Оповещения о новом хакатоне
    }

    if (hackStatus.equals("Canceled")) {
      hackRepository.delete(hackOptional.get());
      eventService.updateEventStatus(eventRepository.findByResourceHackReferenceUuid(id).getId(),
          3);
    }



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
}
