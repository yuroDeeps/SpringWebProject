package pl.yuro.crudandloginexercisepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

	@GetMapping("/homepage")
	public String homepage() {
		return "homepage.html";
	}
	
	@GetMapping("/")
	public String redirectMainPath() {
		return "redirect:/homepage";
	}
}
