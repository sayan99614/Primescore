package com.primescore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class Login_controller {

	@GetMapping("/select-login")
	public String dologin(@RequestParam("type") String type,HttpSession session) {
		return "login";
	}
}
