package com.netcracker.hack.web.user;

import com.netcracker.hack.model.People;
import com.netcracker.hack.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PeopleController {
    @Autowired
    private PeopleRepository repository;

    @GetMapping("/test")
    public String main(Map<String, Object> model) {
        return "test";
    }

    @GetMapping("/main")
    public String viewAll(Map<String, Object> model) {
        Iterable<People> users = repository.findAll();
        model.put("users", users);
        return "demo";
    }

    @PostMapping("/main")
    public String addNewUser(@RequestParam String name, Map<String, Object> model) {
        People user = new People(name);
        repository.save(user);

        Iterable<People> users = repository.findAll();
        model.put("users", users);
        return "demo";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<People> peopleList = repository.findByName(filter);
        model.put("users", peopleList);
        return "demo";
    }
    @GetMapping(path = "/error")
    public String error(Map<String, Object> model) {
        return "error";
    }
}

