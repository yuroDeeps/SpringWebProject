package pl.yuro.crudandloginexercisepage.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories("pl.yuro.crudandloginexercisepage.repository")
public class DemoDataSourceConfig {
	
	
	/*
	 * @Primary
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix="app.datasource") public DataSource
	 * appDataSource() { return DataSourceBuilder.create().build(); }
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix="spring.data.jpa.entity") public
	 * LocalContainerEntityManagerFactoryBean
	 * entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource
	 * appDataSource) { return builder .dataSource(appDataSource).build(); }
	 */
	  @Bean
	  @Primary
	  @ConfigurationProperties(prefix = "spring.datasource")
	  public DataSource dataSource() { return DataSourceBuilder.create().build(); }
	  @Bean
	  public PasswordEncoder encoder() {
	      return new BCryptPasswordEncoder();
	  }


}