package com.netcracker.hack.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(Model model, @RequestParam(name = "loginError", defaultValue = "nope") String loginError) {

		model.addAttribute("loginError", loginError);
		return "open/login";
	}
}
