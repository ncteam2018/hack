package com.netcracker.hack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginContoller {
	
	@RequestMapping(value="/mainPage", method=RequestMethod.GET)
	public String getMain() {
		return "open/mainPage";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin() {
	
		return "open/login";
	}
	
	@RequestMapping(value="/loginError", method=RequestMethod.GET)
	public String getError(Model model) {
		
		model.addAttribute("loginError", true);
		return "open/login";
	}
	
	
	@RequestMapping(value="/securedPage", method=RequestMethod.GET)
	public String getSecret() {
		return "securedPage";
	}
	
}
