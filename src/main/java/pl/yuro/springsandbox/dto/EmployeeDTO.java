package pl.yuro.springsandbox.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	@NotBlank(message = "Uzupełnij imie pracownika!")
	private String firstName;
	
	@NotBlank(message="Uzupełnij nazwisko pracownika!")
	private String lastName;
	
	@NotBlank(message="Uzupełnij mail pracownika!")
	@Email(message="Wprowadź mail poprawnie!")
	private String email;
}
