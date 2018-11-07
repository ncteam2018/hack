package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Request;
import com.netcracker.hack.service.Impl.RequestServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
public class RequestController {

  @Autowired
  private RequestServiceImpl service;

  @ApiOperation("Returns all requests")
  @GetMapping
  public List<Request> getAllRequest() {
    return service.getAllRequests();
  }

  @ApiOperation("Returns request by uuid")
  @GetMapping("/{id}")
  public Request getRequest(
      @ApiParam(value = "Request's uuid", required = true) @PathVariable int id) {
    return service.getRequest(id);
  }

  @ApiOperation("Returns all requests from")
  @GetMapping("/from/{id}")
  public List<Request> getAllRequestsFrom(
      @ApiParam(value = "Author's uuid", required = true) @PathVariable UUID id) {
    return service.getAllRequestsFrom(id);
  }

  @ApiOperation("Returns all requests to")
  @GetMapping("/to/{id}")
  public List<Request> getAllRequestsTo(
      @ApiParam(value = "Resiever's uuid", required = true) @PathVariable UUID id) {
    return service.getAllRequestsTo(id);
  }

  @ApiOperation("Deletes request by uuid")
  @DeleteMapping("/{id}")
  public void deleteRequest(
      @ApiParam(value = "Request's uuid", required = true) @PathVariable int id) {
    service.deleteRequest(id);
  }

  @ApiOperation("Adds new request")
  @PostMapping
  public ResponseEntity<Object> createRequest(
      @ApiParam(value = "new Request", required = true) @RequestBody Request request) {
    return service.createRequest(request);
  }

  @ApiOperation("Updates request by uuid")
  @PutMapping("/{id}")
  public ResponseEntity<Object> updateRequest(
      @ApiParam(value = "new Request", required = true) @RequestBody Request request,
      @ApiParam(value = "Request's uuid", required = true) @PathVariable int id) {
    return service.updateRequest(request, id);
  }
}
