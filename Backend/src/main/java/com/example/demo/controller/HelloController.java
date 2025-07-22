package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/sayHello")
	public String sayHello(){
		return "Hello World";
	}
	
	@RequestMapping("/sayHi")
	public String sayHi(){
		return "Hi World";
	}
	
}
