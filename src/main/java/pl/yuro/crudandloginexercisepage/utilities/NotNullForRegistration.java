package pl.yuro.crudandloginexercisepage.utilities;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.NoArgsConstructor;
import pl.yuro.crudandloginexercisepage.dto.UserDTO;

@NoArgsConstructor
public class NotNullForRegistration {
	
	private UserDTO userDTO;
	
	private BindingResult bindingResult;

	public void NotNullUserDTO(UserDTO userDTO, BindingResult bindingResult) {
		
		this.bindingResult = bindingResult;
		
		this.userDTO = userDTO;
	
		if(userDTO.getEmail()==null) {
			bindingResult.addError(new FieldError("userDTO", "email", "Pole Email musi zostać uzupełnione!"));
		}
		
		if(userDTO.getPassword()==null) {
			bindingResult.addError(new FieldError("userDTO", "password", "Pole Hasło musi zostać uzupełnione!"));
		}
		
		if(userDTO.getRpassword()==null) {
			bindingResult.addError(new FieldError("userDTO", "rpassword", "Hasło musi zostać ponownie wpisane!"));
		}
		
		if(userDTO.getUsername()==null) {
			bindingResult.addError(new FieldError("userDTO", "username", "Pole Username musi zostać uzupełnione!"));
		}
	}
}
