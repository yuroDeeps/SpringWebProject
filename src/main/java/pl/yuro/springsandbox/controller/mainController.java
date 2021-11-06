package pl.yuro.springsandbox.controller;

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
