package com.netcracker.hack.repository;

import com.netcracker.hack.model.Hack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HackRepository extends CrudRepository<Hack, UUID> {
    public List<Hack> findByCompany_Uuid(UUID uuid);
}
