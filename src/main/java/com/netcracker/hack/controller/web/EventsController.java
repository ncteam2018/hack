package com.netcracker.hack.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventsController {
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public String getProfilePage(String loginError) {

        return "events";
    }
}