package com.netcracker.hack.service.Impl;

import com.netcracker.hack.model.Hack;
import com.netcracker.hack.repository.HackRepository;
import com.netcracker.hack.service.HackService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class HackServiceImpl implements HackService {

  @Autowired
  private HackRepository repository;

  public List<Hack> getAllHack() {
    return (List<Hack>) repository.findAll();
  }

  public Hack getHack(UUID id) {
    Optional<Hack> hack = repository.findById(id);
    if (!hack.isPresent()) {
      return new Hack(); // через ошибку
    }
    return hack.get();
  }

  public List<Hack> getHackByCompany(UUID id) {
    return repository.findByCompany_Uuid(id);
  }

  public void deleteHack(UUID id) {
    repository.deleteById(id);
  }

  public ResponseEntity<Object> createHack(Hack hack) {
    Hack savedHack = repository.save(hack);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedHack.getUuid()).toUri();

    return ResponseEntity.created(location).build();
  }

  public ResponseEntity<Object> updateHack(Hack hack, UUID id) {
    Optional<Hack> hackOptional = repository.findById(id);
    if (!hackOptional.isPresent()) {
      return ResponseEntity.notFound().build();
    }
    hack.setUuid(id);
    repository.save(hack);
    return ResponseEntity.noContent().build();
  }


}
