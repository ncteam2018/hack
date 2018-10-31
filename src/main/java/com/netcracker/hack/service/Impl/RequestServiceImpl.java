package com.netcracker.hack.service.Impl;

import com.netcracker.hack.model.Request;
import com.netcracker.hack.repository.RequestRepository;
import com.netcracker.hack.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository repository;

    public List<Request> getAllRequests() {
        return (List<Request>) repository.findAll();
    }

    public Request getRequest(int id) {
        Optional<Request> request = repository.findById(id);
        return request.get();
    }

    public List<Request> getAllRequestsFrom(UUID id) {
        return repository.findAllByFromId(id);
    }

    public List<Request> getAllRequestsTo(UUID id) {
        return repository.findAllByToId(id);
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
