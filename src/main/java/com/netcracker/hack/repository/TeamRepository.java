package com.netcracker.hack.repository;

import com.netcracker.hack.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeamRepository extends CrudRepository<Team, UUID> {
}
