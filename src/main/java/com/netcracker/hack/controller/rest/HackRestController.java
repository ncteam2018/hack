package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Hack;
import com.netcracker.hack.service.HackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/hack")
public class HackRestController {
    @Autowired
    private HackService service;

    @ApiOperation("Returns all hacks")
    @GetMapping
    public List<Hack> getAllHack() {
        return service.getAllHack();
    }

    @ApiOperation("Returns hack by uuid")
    @GetMapping("/{id}")
    public Hack getHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
        return service.getHack(id);
    }

    @ApiOperation("Deletes hack profile by uuid")
    @DeleteMapping("/{id}")
    public void deleteHack(@ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
        service.deleteHack(id);
    }

    @ApiOperation("Adds new hack")
    @PostMapping
    public ResponseEntity<Object> createHack(@ApiParam(value = "new Hack") @RequestBody Hack hack) {
        return service.createHack(hack);
    }

    @ApiOperation("Updates hack by uuid")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHack(@ApiParam(value = "new Hack", required = true) @RequestBody Hack hack,
                                             @ApiParam(value = "Hack's uuid", required = true) @PathVariable UUID id) {
        return service.updateHack(hack, id);
    }
}
