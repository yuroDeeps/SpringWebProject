package pl.yuro.springsandbox.controller;


import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.yuro.springsandbox.dto.UserDTO;
import pl.yuro.springsandbox.entity.Role;
import pl.yuro.springsandbox.entity.User;
import pl.yuro.springsandbox.entity.VerificationToken;
import pl.yuro.springsandbox.repository.RoleRepository;
import pl.yuro.springsandbox.service.UserDetailsServiceImpl;
import pl.yuro.springsandbox.service.VerificationTokenService;
import pl.yuro.springsandbox.utilities.NotNullForRegistration;

@Validated
@Controller
public class registerController {

	

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrmmer = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrmmer);
		
	}
	
 	private final UserDetailsServiceImpl userDetailsServiceImpl;
 	
 	public final VerificationTokenService verificationTokenService;

    private final RoleRepository roleRepository;
	
	@Autowired
	public registerController(UserDetailsServiceImpl userDetailsServiceImpl,VerificationTokenService verificationTokenService, RoleRepository roleRepository) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.verificationTokenService = verificationTokenService;
		this.roleRepository = roleRepository;
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute UserDTO userDTO, Model model) {
		model.addAttribute("userDTO", userDTO);
		return "register";
	}
	
	@PostMapping("/register")
	public String save(@Validated @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult) {
		
		if(userDetailsServiceImpl.userExists(userDTO.getEmail(),userDTO.getUsername())) {
			bindingResult.addError(new FieldError("userDTO", "email", "Email lub nazwa u??ytkownika, kt??r?? poda??e?? jest ju?? zaj??ta!"));
		}
		
		NotNullForRegistration notNullForReg = new NotNullForRegistration();
		notNullForReg.NotNullUserDTO(userDTO, bindingResult);
		
		if(userDTO.getPassword() !=null && userDTO.getRpassword()!=null) {
			if(!(userDTO.comparePassAndRPass(userDTO.getPassword(), userDTO.getRpassword()))) {
				bindingResult.addError(new FieldError("userDTO", "rpassword", "Wpsiane has??a musz?? by?? takie same!"));
			}
		}
		
		if(bindingResult.hasErrors()) {
			return "register";
		}
		userDetailsServiceImpl.register(userDTO);
		return "redirect:/login";

	}
	
	@GetMapping("/emailconfirmation")
	public String verification(@RequestParam("token") String token, Model model) {
		
		VerificationToken verificationToken = verificationTokenService.findByToken(token);
		if(verificationToken==null) {
			model.addAttribute("message", "Nieprawid??owy klucz weryfikacji.");
		} else {
			User user = verificationToken.getUser();
			
			if(user.isEnabled()) {
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				
				if(verificationToken.getExpireTime().before(currentTimestamp)) {
					model.addAttribute("message", "Twoja mo??liwo???? weryfikacji wygas??a");
				}else {
					//Token
					Set<Role> roles = new HashSet<Role>();
					Role role = roleRepository.getRoleByRoleName("VERIFIED");
					roles.add(role);
					user.setRoles(roles);
					userDetailsServiceImpl.save(user);
					
					model.addAttribute("message", "Email zosta?? poprawnie zweryfikowany.");
					
				}
			} else {
				model.addAttribute("message", "Tw??j email zosta?? ju?? zweryfikowany wcze??niej.");
			}
		}
		
		return "verification-form";
	}

	
}
