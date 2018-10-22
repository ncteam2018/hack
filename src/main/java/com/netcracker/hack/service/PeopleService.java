package com.netcracker.hack.service;

import com.netcracker.hack.model.People;
import com.netcracker.hack.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class PeopleService {
    @Autowired
    private PeopleRepository repository;

    public List<People> retrieveAllUsers() {
        return (List<People>) repository.findAll();
    }

    public People retrievePeople(int id){
        Optional<People> people = repository.findById(id);
        return people.get();
    }

    public void deletePeople (int id){
        repository.deleteById(id);
    }

    public ResponseEntity<Object> createPeople (People people){
        People savedPeople = repository.save(people);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPeople.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updatePeople (People people, int id){
        Optional<People> peopleOptional = repository.findById(id);
        if (!peopleOptional.isPresent())
            return ResponseEntity.notFound().build();
        people.setId(id);
        repository.save(people);
        return ResponseEntity.noContent().build();
    }
}
