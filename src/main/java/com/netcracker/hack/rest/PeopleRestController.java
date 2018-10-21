package com.netcracker.hack.rest;

import com.netcracker.hack.model.People;
import com.netcracker.hack.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PeopleRestController {
    @Autowired
    private PeopleRepository repository;

    @GetMapping("/people")
    public List<People> retrieveAllUsers() {
        return (List<People>) repository.findAll();
    }
    @GetMapping("/people/{id}")
    public People retrieveStudent(@PathVariable int id){
        Optional<People> people = repository.findById(id);
        return people.get();
    }
    @DeleteMapping("/people/{id}")
    public void deletePeople (@PathVariable int id){
        repository.deleteById(id);
    }
    @PostMapping("/people")
    public ResponseEntity<Object> createPeople (@RequestBody People people){
        People savedPeople = repository.save(people);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPeople.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/people/{id}")
    public ResponseEntity<Object> updatePeople (@RequestBody People people, @PathVariable int id){
        Optional<People> peopleOptional = repository.findById(id);
        if (!peopleOptional.isPresent())
            return ResponseEntity.notFound().build();
        people.setId(id);
        repository.save(people);
        return ResponseEntity.noContent().build();
    }
}