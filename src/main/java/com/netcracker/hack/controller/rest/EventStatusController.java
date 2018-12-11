package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.EventStatus;
import com.netcracker.hack.service.Impl.EventStatusServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/eventstatus")
public class EventStatusController {
    @Autowired
    private EventStatusServiceImpl service;

    @ApiOperation("Returns all users")
    @GetMapping
    public List<EventStatus> getAllProfile() {
        return service.getAllStatus();
    }
}
