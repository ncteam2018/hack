package com.netcracker.hack.service;

import com.netcracker.hack.model.Hack;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface HackService {
    List<Hack> getAllHack();

    Hack getHack(UUID id);

    List<Hack> getHackByCompany(UUID id);

    void deleteHack(UUID id);

    ResponseEntity<Object> createHack(Hack hack);

    ResponseEntity<Object> updateHack(Hack hack, UUID id);
}
