package com.netcracker.hack.rest;

import com.netcracker.hack.model.Hack;
import com.netcracker.hack.service.HackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class HackRestController {
    @Autowired
    private HackService service;

    @GetMapping("/api/hack")
    public List<Hack> retrieveAllHack() {
        return service.retrieveAllHack();
    }

    @GetMapping("/api/hack/{id}")
    public Hack retrieveHack(@PathVariable UUID id){
        return service.retrieveHack(id);
    }

    @DeleteMapping("/api/hack/{id}")
    public void deleteHack (@PathVariable UUID id){
        service.deleteHack(id);
    }

    @PostMapping("/api/hack")
    public ResponseEntity<Object> createHack (@RequestBody Hack hack){
        return createHack(hack);
    }

    @PutMapping("/api/hack/{id}")
    public ResponseEntity<Object> updateHack (@RequestBody Hack hack, @PathVariable UUID id){
        return service.updateHack(hack, id);
    }
}
