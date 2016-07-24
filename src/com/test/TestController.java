package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class TestController {
	@RequestMapping("/home")
	public String hello() {
		System.out.print("ds");
		return "home";
	}
	
}
