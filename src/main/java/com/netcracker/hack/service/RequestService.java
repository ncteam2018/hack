package com.netcracker.hack.service;

import com.netcracker.hack.model.Request;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface RequestService {

  public List<Request> getAllRequests();

  public Request getRequest(int id);

  public List<Request> getAllRequestsFrom(UUID id);

  public List<Request> getAllRequestsTo(UUID id);

  public void deleteRequest(int id);

  public ResponseEntity<Object> createRequest(Request request);

  public ResponseEntity<Object> updateRequest(Request request, int id);
}
