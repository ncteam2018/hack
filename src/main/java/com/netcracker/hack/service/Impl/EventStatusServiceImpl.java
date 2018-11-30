package com.netcracker.hack.service.Impl;

import com.netcracker.hack.model.EventStatus;
import com.netcracker.hack.repository.EventStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventStatusServiceImpl {
    @Autowired
    private EventStatusRepository repository;

    public List<EventStatus> getAllStatus(){
        return (List<EventStatus>) repository.findAll();
    }
}
