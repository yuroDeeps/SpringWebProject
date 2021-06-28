package pl.yuro.crudandloginexercisepage.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.yuro.crudandloginexercisepage.dto.PasswdChangeDTO;
import pl.yuro.crudandloginexercisepage.dto.UserDTO;
import pl.yuro.crudandloginexercisepage.entity.User;
import pl.yuro.crudandloginexercisepage.entity.VerificationToken;
import pl.yuro.crudandloginexercisepage.service.EmailService;
import pl.yuro.crudandloginexercisepage.service.UserDetailsServiceImpl;
import pl.yuro.crudandloginexercisepage.service.VerificationTokenService;
import pl.yuro.crudandloginexercisepage.utilities.NotNullForRegistration;

@Controller
@RequestMapping()
public class changePasswordController {
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	VerificationTokenService verificationTokenServce;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/forgotPassword")
	public String forgotPassword(@ModelAttribute String email, Model model) {
		model.addAttribute(email);
		
		return "forgot-password.html";
	}
	
	@PostMapping("/resetPassword")
	public String resetPassword(@ModelAttribute("email") String email) {
		emailService.sendPasswordChange(email);
		return "redirect:/homepage";
	}
	
	@GetMapping("/changePassword")
	public String changePassword(@RequestParam("token") String token, Model model, HttpSession session) {
		VerificationToken userToken = verificationTokenServce.findByToken(token);
		User user = userToken.getUser();
		System.out.println(user);
		PasswdChangeDTO userDTO = new PasswdChangeDTO();
		
		session.setAttribute("user", user);
		model.addAttribute("userDTO",userDTO);
		
		return "change-password.html";			
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@Validated @ModelAttribute("userDTO") PasswdChangeDTO userDTO, HttpSession session, BindingResult bindingResult) {
		

		
		User user = null;
	    if (session.getAttribute("user") != null) {
	        user = (User) session.getAttribute("user");
	        System.out.println(user);
	    }
		
		bindingResult = userService.changePasswordSave(user,userDTO,bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "/changePassword";
		}
		
		System.out.println("Kontrolnie 5");
		return "redirect:/homepage";
	}

}
