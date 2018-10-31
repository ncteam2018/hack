package com.netcracker.hack.repository;

import com.netcracker.hack.model.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProfileRepository extends CrudRepository<Profile, UUID> {
    public Profile findByLogin(String login);
}
