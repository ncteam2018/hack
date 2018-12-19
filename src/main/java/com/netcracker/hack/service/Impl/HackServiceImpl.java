package com.netcracker.hack.service.Impl;

import com.netcracker.hack.dto.HackDTO;
import com.netcracker.hack.dto.UserDTO;
import com.netcracker.hack.dto.builder.PageRequestBuilder;
import com.netcracker.hack.model.Hack;
import com.netcracker.hack.model.Team;
import com.netcracker.hack.repository.CompanyRepository;
import com.netcracker.hack.repository.EventRepository;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.repository.TeamRepository;
import com.netcracker.hack.service.EventService;
import com.netcracker.hack.service.HackService;
import com.netcracker.hack.service.ProfileService;
import com.netcracker.hack.service.TagsService;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class HackServiceImpl implements HackService {

  @Autowired
  private HackRepository hackRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TagsService tagService;

  @Autowired
  private EventService eventService;

  @Autowired
  private TeamRepository teamRepository;

  @Override
  public Set<String> getAllHackPlaces() {

    Set<String> cities = new HashSet<>();
    for (String city : hackRepository.findAllCities())
      cities.add(city.split(",")[1]);

    return cities;
  }

  @Override
  public Set<String> getAllCompNames() {

    return companyRepository.findDistinctCompNames();
  }

  @Override
  public List<Pair<Object, Object>> getAllHackNames() {

    List<Tuple> resultTuple = hackRepository.findAllName();
    List<Pair<Object, Object>> nameUuidPairs = new ArrayList<>();

    for (Tuple pair : resultTuple)
      nameUuidPairs.add(Pair.of(pair.get(0), pair.get(1)));



    return nameUuidPairs;
  }

  public Page<HackDTO> getFilteredHacks(PageRequestBuilder requestBuilder) {
    Page<Hack> hackPage;

    if (requestBuilder.isFiltered()) {
      if (requestBuilder.getSkillTags().size() > 0 && requestBuilder.getScopeTags().size() > 0)
        hackPage = hackRepository
            .findDistinctBySkillTagsInAndScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getScopeTags(), requestBuilder.getSearchName(),
                requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName(),
                "Active");
      else if (requestBuilder.getSkillTags().size() > 0)
        hackPage = hackRepository
            .findDistinctBySkillTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
                requestBuilder.getPageRequest(), requestBuilder.getSkillTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName(), "Active");
      else
        hackPage = hackRepository
            .findDistinctByScopeTagsInAndNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
                requestBuilder.getPageRequest(), requestBuilder.getScopeTags(),
                requestBuilder.getSearchName(), requestBuilder.getSearchCompanyName(),
                requestBuilder.getSearchCityName(), "Active");
    } else
      hackPage = hackRepository
          .findDistinctByNameContainsAndCompanyCompanyNameContainsAndPlaceContainsAndStatusContains(
              requestBuilder.getPageRequest(), requestBuilder.getSearchName(),
              requestBuilder.getSearchCompanyName(), requestBuilder.getSearchCityName(), "Active");

    PageImpl<HackDTO> hackDTOPage = new PageImpl<HackDTO>(makeListOfHackDTO(hackPage.getContent()),
        hackPage.getPageable(), hackPage.getTotalElements());

    return hackDTOPage;
  }

  public HackDTO getHack(UUID id) {
    return new HackDTO(hackRepository.findByUuid(id));
  }

  public List<Hack> getHackByCompany(UUID id) {
    return null;// repository.findByCompany_Uuid(id);
  }

  public void deleteHack(UUID hackID) {

    Hack hack = hackRepository.findByUuid(hackID);
    hack.setStatus("Closed");
    hackRepository.save(hack);

    eventService.createNotification("Хакатон отменён!", hackID, null, null);

    teamRepository.findByHackUuid(hackID).forEach((Team team) -> {
      eventService.createNotification(
          "Хакатон больше не действителен, смените его или команда будет удалена!", hackID,
          team.getUuid(), null);
      team.setStatus("Completed");
      // TODO: team.setDeleteTime();
      teamRepository.save(team);
    });
  }

  public ResponseEntity<Object> createHack(HackDTO hackDTO, String creatorName) {

    hackDTO.setSkillTags(tagService.verifyTags(hackDTO.getSkillTags()));
    hackDTO.setScopeTags(tagService.verifyTags(hackDTO.getScopeTags()));

    UserDTO companyProfile = profileService.getUserDTOByLogin(creatorName);
    hackDTO.setCompany(companyProfile.getCompanyData());
    hackDTO.setStatus("Active");
    Hack savedHack = hackRepository.save(new Hack(hackDTO));

    eventService.createEvent(1, 2, companyProfile.getUuid(),
        UUID.fromString("00000000-0000-0000-0000-000000000000"), savedHack.getUuid(), null,
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

      // TODO: Сдалать нормальный поиск нужного события
      eventService.updateEventStatus(eventRepository.findByHackUuid(id).get(0).getId(), 1);

      eventService.createNotification("Объявлен новый хакатон, спешите принять участие!",
          hackDTO.getUuid(), null, null);
    }

    if (hackStatus.equals("Canceled")) {
      hackRepository.delete(hackOptional.get());
      eventService.updateEventStatus(eventRepository.findByHackUuid(id).get(0).getId(), 3);
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
