package com.netcracker.hack.service;

import com.netcracker.hack.model.Hack;
import com.netcracker.hack.repository.HackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class HackService {
    @Autowired
    private HackRepository repository;

    public List<Hack> retrieveAllHack() {
        return (List<Hack>) repository.findAll();
    }

    public Hack retrieveHack(UUID id){
        Optional<Hack> hack = repository.findById(id);
        return hack.get();
    }

    public void deleteHack (UUID id){
        repository.deleteById(id);
    }

    public ResponseEntity<Object> createHack (Hack hack){
        Hack savedHack = repository.save(hack);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedHack.getUuid()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updateHack (Hack hack, UUID id){
        Optional<Hack> hackOptional = repository.findById(id);
        if (!hackOptional.isPresent())
            return ResponseEntity.notFound().build();
        hack.setUuid(id);
        repository.save(hack);
        return ResponseEntity.noContent().build();
    }
}
