package pl.yuro.crudandloginexercisepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
