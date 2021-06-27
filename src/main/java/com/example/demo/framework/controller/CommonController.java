package com.example.demo.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.framework.service.UserService;

@RestController
public class CommonController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String defaultM() {
		return "hello";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}
	
	@RequestMapping("/add-user")
	public ModelAndView addUser(String username,String password,String role) {
		
		//userService.saveUserToMemory(username, password, role);
		userService.saveUserToDatabase(username, password, role);
		
		return new ModelAndView("redirect:/login");
	}
}
