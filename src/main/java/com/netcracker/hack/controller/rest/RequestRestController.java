package com.netcracker.hack.controller.rest;

import com.netcracker.hack.model.Request;
import com.netcracker.hack.service.RequestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestRestController {
    @Autowired
    private RequestService service;

    @ApiOperation("Returns all requests")
    @GetMapping
    public List<Request> getAllRequest() {
        return service.getAllRequests();
    }

    @ApiOperation("Returns request by uuid")
    @GetMapping("/{id}")
    public Request getRequest(@ApiParam(value = "Request's uuid", required = true) @PathVariable int id) {
        return service.getRequest(id);
    }

    @ApiOperation("Deletes request by uuid")
    @DeleteMapping("/{id}")
    public void deleteRequest(@ApiParam(value = "Request's uuid", required = true) @PathVariable int id) {
        service.deleteRequest(id);
    }

    @ApiOperation("Adds new request")
    @PostMapping
    public ResponseEntity<Object> createRequest(@ApiParam(value = "new Request", required = true) @RequestBody Request request) {
        return service.createRequest(request);
    }

    @ApiOperation("Updates request by uuid")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRequest(@ApiParam(value = "new Request", required = true) @RequestBody Request request,
                                                @ApiParam(value = "Request's uuid", required = true)@PathVariable int id) {
        return service.updateRequest(request, id);
    }
}
