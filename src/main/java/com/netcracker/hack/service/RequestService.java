package com.netcracker.hack.service;

import com.netcracker.hack.model.Request;
import com.netcracker.hack.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class RequestService {
    @Autowired
    private RequestRepository repository;

    public List<Request> retrieveAllRequests() {
        return (List<Request>) repository.findAll();
    }

    public Request retrieveRequest(int id) {
        Optional<Request> request = (Optional<Request>) repository.findById(id);
        return request.get();
    }

    public void deleteRequest(int id) {
        repository.deleteById(id);
    }

    public ResponseEntity<Object> createRequest(Request request) {
        Request savedRequest = repository.save(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRequest.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Object> updateRequest(Request request, int id) {
        Optional<Request> requestOptional = repository.findById(id);
        if (!requestOptional.isPresent())
            return ResponseEntity.notFound().build();
        request.setId(id);
        repository.save(request);
        return ResponseEntity.noContent().build();
    }
}
