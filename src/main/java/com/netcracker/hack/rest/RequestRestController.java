package com.netcracker.hack.rest;

import com.netcracker.hack.model.Request;
import com.netcracker.hack.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestRestController {
    @Autowired
    private RequestService service;

    @GetMapping("/api/request")
    public List<Request> retrieveAllRequest() {
        return service.retrieveAllRequests();
    }

    @GetMapping("/api/request/{id}")
    public Request retrieveRequest(@PathVariable int id) {
        return service.retrieveRequest(id);
    }

    @DeleteMapping("/api/request/{id}")
    public void deleteRequest(@PathVariable int id) {
        service.deleteRequest(id);
    }

    @PostMapping("/api/request")
    public ResponseEntity<Object> createRequest(@RequestBody Request request) {
        return service.createRequest(request);
    }

    @PutMapping("/api/request/{id}")
    public ResponseEntity<Object> updateRequest(@RequestBody Request request, @PathVariable int id) {
        return service.updateRequest(request, id);
    }
}
