package pl.yuro.springsandbox.dto;

import org.hibernate.validator.constraints.Length;

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
public class UserDTO {

	private String username;

	@Length(min = 8, message="Hasło musi posiadać ponad 8 znaków!")
	private String password;

	private String rpassword;

	private String email;
	
	public boolean comparePassAndRPass(String password, String rpassword) {
		this.password = password;
		this.rpassword = rpassword;
		if(password.equals(rpassword)) {
			return true;
		}
		return false;
	}
	
	
}
