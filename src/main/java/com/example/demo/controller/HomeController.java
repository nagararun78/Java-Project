package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String defaultM() {
		return "hello";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}
}
