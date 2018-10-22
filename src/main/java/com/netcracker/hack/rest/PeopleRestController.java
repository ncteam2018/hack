package com.netcracker.hack.rest;

import com.netcracker.hack.model.People;
import com.netcracker.hack.repository.PeopleRepository;
import com.netcracker.hack.service.PeopleService;
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
    private PeopleService service;

    @GetMapping("/api/people")
    public List<People> retrieveAllUsers() {
        return service.retrieveAllUsers();
    }
    @GetMapping("/api/people/{id}")
    public People retrieveStudent(@PathVariable int id){
        return service.retrieveStudent(id);
    }
    @DeleteMapping("/api/people/{id}")
    public void deletePeople (@PathVariable int id){
        service.deletePeople(id);
    }
    @PostMapping("/api/people")
    public ResponseEntity<Object> createPeople (@RequestBody People people){
        return createPeople(people);
    }
    @PutMapping("/api/people/{id}")
    public ResponseEntity<Object> updatePeople (@RequestBody People people, @PathVariable int id){
       return service.updatePeople(people, id);
    }
}