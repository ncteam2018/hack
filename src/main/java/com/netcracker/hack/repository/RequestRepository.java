package com.netcracker.hack.repository;

import com.netcracker.hack.model.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RequestRepository extends CrudRepository<Request, UUID> {
}
