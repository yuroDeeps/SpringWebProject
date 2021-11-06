package pl.yuro.springsandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
	
	@GetMapping("/login")
	public String loginPage() {
		return "login-page.html";
	}
	
	@GetMapping("/acces-denied")
	public String accesDeniedForm() {
		return "acces-denied";
	}
	
	@GetMapping("/unverified")
	public String unverifiedForm() {
		return "unverified-form";
	}

}
