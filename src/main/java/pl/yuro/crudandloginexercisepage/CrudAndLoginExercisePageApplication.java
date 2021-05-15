package pl.yuro.crudandloginexercisepage;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class CrudAndLoginExercisePageApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(CrudAndLoginExercisePageApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
