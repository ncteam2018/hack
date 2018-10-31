package com.netcracker.hack.repository;

import com.netcracker.hack.model.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    public List<Request> findAllByFromId(UUID uuid);

    public List<Request> findAllByToId(UUID uuid);
}
