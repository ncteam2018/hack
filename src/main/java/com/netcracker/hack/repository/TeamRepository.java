package com.netcracker.hack.repository;

import com.netcracker.hack.model.Team;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, UUID> {

}
