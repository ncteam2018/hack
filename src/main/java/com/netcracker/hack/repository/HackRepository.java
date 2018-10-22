package com.netcracker.hack.repository;

import com.netcracker.hack.model.Hack;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface HackRepository extends CrudRepository<Hack, UUID> {
}
