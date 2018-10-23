package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Team;
import com.netcracker.hack.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TeamRestController {
    @Autowired
    private TeamService service;

    @GetMapping("/api/team")
    public List<Team> retrieveAllTeams() {
        return service.retrieveAllTeams();
    }

    @GetMapping("/api/team/{id}")
    public Team retrieveTeam(@PathVariable UUID id) {
        return service.retrieveTeam(id);
    }

    @DeleteMapping("/api/team/{id}")
    public void deleteTeam(@PathVariable UUID id) {
        service.deleteTeam(id);
    }

    @PostMapping("/api/team")
    public ResponseEntity<Object> createTeam(@RequestBody Team team) {
        return service.createTeam(team);
    }

    @PutMapping("/api/team/{id}")
    public ResponseEntity<Object> updateTeam(@RequestBody Team team, @PathVariable UUID id) {
        return service.updateTeam(team, id);
    }
}
