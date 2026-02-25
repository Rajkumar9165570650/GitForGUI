package com.raj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.raj.exception.AccessDenidedException;
import com.raj.exception.CustomAuthenticationException;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private MyUserDetails detals;

	@Autowired
	private CustomAuthenticationException customAuthenticationException;

	@Autowired
	private AccessDenidedException  accessDenidedException;

	@Autowired
	private JWTFilter fileter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {

		http.authorizeHttpRequests(autho->autho
				.requestMatchers("/student-api/add","/student-api/findById/{id}","/student-api/findAll","/login-api/**").permitAll()
				.requestMatchers("/student-api/update/{id}/{className}","/student-api/update","/student-api/delete/{id}").hasRole("ADMIN")
				.anyRequest().authenticated()
				);
		http.csrf(csrf->csrf.disable());
		http.sessionManagement(sesson->sesson.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(basic->basic.disable()); 
		http.addFilterBefore(fileter, UsernamePasswordAuthenticationFilter.class);
		http.userDetailsService(detals);
		http.formLogin(login->login.disable());
		http.exceptionHandling(exception->exception
				.authenticationEntryPoint(customAuthenticationException)
				.accessDeniedHandler(accessDenidedException)
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager manger(AuthenticationConfiguration config) throws Exception {
		return  config.getAuthenticationManager();
	}
}
