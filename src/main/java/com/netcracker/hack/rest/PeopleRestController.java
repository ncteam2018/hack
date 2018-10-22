package com.netcracker.hack.rest;

import com.netcracker.hack.model.People;
import com.netcracker.hack.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeopleRestController {
    @Autowired
    private PeopleService service;

    @GetMapping("/api/people")
    public List<People> retrieveAllUsers() {
        return service.retrieveAllUsers();
    }

    @GetMapping("/api/people/{id}")
    public People retrievePeople(@PathVariable int id){
        return service.retrievePeople(id);
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