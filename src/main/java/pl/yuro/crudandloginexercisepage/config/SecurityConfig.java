package pl.yuro.crudandloginexercisepage.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import pl.yuro.crudandloginexercisepage.service.UserDetailsServiceImpl;
import pl.yuro.crudandloginexercisepage.utilities.CustomSuccesHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("pl.yuro.crudandloginexercisepage")
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	CustomSuccesHandler customSuccesHandler;
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	
	
	  //@Autowired
	  //@Qualifier("securityDataSource") private DataSource securityDataSource;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());//.jdbcAuthentication().dataSource(securityDataSource);
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/unverified").hasAuthority("UNVERIFIED")
			.antMatchers("/homepage","/","/register","/emailconfirmation").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").successHandler(customSuccesHandler)
			.permitAll().and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/homepage")
			.deleteCookies("JSESSIONID")
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
}
