package pl.yuro.crudandloginexercisepage.dto;

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
public class PasswdChangeDTO {
	
	@Length(min = 8, message="Hasło musi posiadać ponad 8 znaków!")
	private String password;
	private String rpassword;
	
	public boolean comparePassAndRPass(String password, String rpassword) {
		this.password = password;
		this.rpassword = rpassword;
		if(password.equals(rpassword)) {
			System.out.println("Kontrolnie es");
			return true;
		}
		return false;
	}

}
