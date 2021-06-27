package com.example.demo.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.framework.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class DemoWebSecuirty extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Custom Security");
		http.authorizeRequests()
		.antMatchers("/","/home","/add-user").permitAll()
		.anyRequest().authenticated();
		
		http.formLogin();
		http.httpBasic();
		
		http.csrf().disable();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 
	/*
	 * @Bean public PasswordEncoder customPasswordEncoder() { return new
	 * CustomPasswordEncoder(); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// UserDetailService Type Authentication
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		// In Memory Type Authentication
		/*
		 * auth.inMemoryAuthentication().withUser("Krishna") .password(
		 * "$2a$10$4Iz6tNv.XPQATB8IASt7Ye.D2SJfojIPFKsppZKs35RR8PeqraWgK")
		 * .roles("ADMIN");
		 */
		 
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}
}
