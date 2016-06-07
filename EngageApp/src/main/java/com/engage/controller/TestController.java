package com.engage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	String message = "Welcome to Spring MVC!";
	
	@RequestMapping("/")
	public String showHome(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,ModelMap map) {
		System.out.println("in controller");
 
		map.put("message", message);
		map.put("name", name);
		return "hello";
	}
	
	@RequestMapping("/hello")
	public String showHello(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,ModelMap map) {
		System.out.println("in controller");
 
		map.put("message", message);
		map.put("name", name);
		return "hello";
	}
}
