package pl.yuro.springsandbox.utilities;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.NoArgsConstructor;
import pl.yuro.springsandbox.dto.EmployeeDTO;
import pl.yuro.springsandbox.dto.UserDTO;

@Component
@NoArgsConstructor
public class NotNullForRegistration {
	
	private EmployeeDTO employeeDTO;
	
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
	
		public void NotNullEmployeeDTO(EmployeeDTO employeeDTO, BindingResult bindingResult) {
		
		this.bindingResult = bindingResult;
		
		this.employeeDTO = employeeDTO;
	
		if(employeeDTO.getEmail()==null) {
			bindingResult.addError(new FieldError("employeeDTO", "email", "Pole Email musi zostać uzupełnione!"));
		}
		
		if(employeeDTO.getFirstName()==null) {
			bindingResult.addError(new FieldError("employeeDTO", "firstName", "Pole Imie musi zostać uzupełnione!"));
		}
		
		if(employeeDTO.getLastName()==null) {
			bindingResult.addError(new FieldError("employeeDTO", "lastName", "Pole Nazwisko musi zostać uzupełnione!"));
		}
	}
}
