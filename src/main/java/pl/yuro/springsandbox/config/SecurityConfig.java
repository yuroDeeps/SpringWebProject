package pl.yuro.springsandbox.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import pl.yuro.springsandbox.service.UserDetailsServiceImpl;
import pl.yuro.springsandbox.utilities.CustomSuccesHandler;

@Configuration
@EnableWebSecurity
@ComponentScan("pl.yuro.springsandbox")
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
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/unverified").hasAuthority("UNVERIFIED")
			.antMatchers("/crud/**").hasAnyAuthority("MANAGER","ADMIN")
			.antMatchers("/currency/**").hasAnyAuthority("MANAGER","ADMIN")
			.antMatchers("/homepage","/","/register","/emailconfirmation").permitAll()
			.antMatchers("/resetPassword/**").permitAll()
			.antMatchers("/forgotPassword/**").permitAll()
			.antMatchers("/changePassword/**").permitAll()
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
