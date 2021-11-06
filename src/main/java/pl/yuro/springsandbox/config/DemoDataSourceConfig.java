package pl.yuro.springsandbox.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories("pl.yuro.springsandbox.repository")
public class DemoDataSourceConfig {

	  @Bean
	  @Primary
	  @ConfigurationProperties(prefix = "spring.datasource")
	  public DataSource dataSource() { return DataSourceBuilder.create().build(); }

	  @Bean
	  public PasswordEncoder encoder() {
	      return new BCryptPasswordEncoder();
	  }
}