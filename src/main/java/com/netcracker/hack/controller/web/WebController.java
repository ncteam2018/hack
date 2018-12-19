package com.netcracker.hack.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

  @RequestMapping(path = "/events", method = RequestMethod.GET)
  public String getEventPage() {

    return "events";
  }

  @RequestMapping(path = "/hacks", method = RequestMethod.GET)
  public String getHackListPage() {

    return "hacks";
  }

  @RequestMapping(path = "/createHack", method = RequestMethod.GET)
  public String getCreateHackPage() {

    return "hackRegistration";
  }

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String login(Model model,
      @RequestParam(name = "loginError", required = false) String loginError) {

    if(loginError != null)
      model.addAttribute("loginError", loginError);
    return "open/login";
  }
  
  @RequestMapping(path = "/", method = RequestMethod.GET)
  public String getMainPage() {
    return "open/index";
  }


  @RequestMapping(path = "/profile", method = RequestMethod.GET)
  public String getProfilePage() {

    return "profilePage";
  }

  @RequestMapping(path = "/registration", method = RequestMethod.GET)
  public String getRegistrationPage() {

    return "open/registration";
  }

  @RequestMapping(path = "/teams", method = RequestMethod.GET)
  public String getTeamListPage(Model model,
      @RequestParam(name = "hackName", defaultValue = "") String hackName) {

    model.addAttribute("hackName", hackName);
    return "teams";
  }

  @RequestMapping(path = "/createTeam", method = RequestMethod.GET)
  public String getCreateTeamPage() {

    return "teamRegistration";
  }

  @RequestMapping(path = "/teamProfile/{uuid}", method = RequestMethod.GET)
  public String getTeamProfilePage(Model model,
      @PathVariable(name = "uuid", required = true) String teamUuid) {

    model.addAttribute("teamUuid", teamUuid);
    return "teamProfilePage";
  }

  @RequestMapping(path = "/teamProfile/{uuid}/update", method = RequestMethod.GET)
  public String getTeamUpdatePage(Model model,
      @PathVariable(name = "uuid", required = true) String teamUuid) {

    model.addAttribute("teamUuid", teamUuid);
    return "updateTeamPage";
  }

  @RequestMapping(path = "/hackPage/{uuid}", method = RequestMethod.GET)
  public String getHackPage(Model model,
      @PathVariable(name = "uuid", required = true) String hackUuid) {
    
    model.addAttribute("hackUuid", hackUuid);
    return "hackPage";
  }

  @RequestMapping(path = "/profileView/{uuid}", method = RequestMethod.GET)
  public String getProfileView(Model model,
      @PathVariable(name = "uuid", required = true) String userUuid) {
    
    model.addAttribute("userUuid", userUuid);
    return "profileView";
  }
  


}
