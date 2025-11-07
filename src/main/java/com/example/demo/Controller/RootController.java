package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class RootController {
	 @GetMapping("/")
	  public String ok(){ return "Doctor Appointment API is running ✅"; }

}
