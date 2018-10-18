package com.netcracker.hack.repository;

import com.netcracker.hack.model.People;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Integer> {
    List<People> findByName(String name);
}