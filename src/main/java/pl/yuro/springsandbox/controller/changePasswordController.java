package pl.yuro.springsandbox.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.yuro.springsandbox.dto.PasswdChangeDTO;
import pl.yuro.springsandbox.entity.User;
import pl.yuro.springsandbox.entity.VerificationToken;
import pl.yuro.springsandbox.service.EmailService;
import pl.yuro.springsandbox.service.UserDetailsServiceImpl;
import pl.yuro.springsandbox.service.VerificationTokenService;

@Controller
@RequestMapping()
public class changePasswordController {
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	VerificationTokenService verificationTokenService;
	
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
		VerificationToken userToken = verificationTokenService.findByToken(token);
		User user = userToken.getUser();
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
	    }
		
		bindingResult = userService.changePasswordSave(user,userDTO,bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "/changePassword";
		}
		return "redirect:/homepage";
	}

}
