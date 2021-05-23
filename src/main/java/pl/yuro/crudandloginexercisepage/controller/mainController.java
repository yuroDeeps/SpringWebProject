package pl.yuro.crudandloginexercisepage.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class mainController {


	@GetMapping("/homepage")
	public String homepage() {
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String redirectMainPath() {
		return "homepage.html";
	}
}
