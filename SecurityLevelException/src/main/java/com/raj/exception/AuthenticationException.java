package com.raj.exception;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class AuthenticationException implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse res,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
		ErrorPage e=new ErrorPage("Your Password /User name is Worng",HttpStatus.UNAUTHORIZED.value(),LocalTime.now());
		
		ObjectMapper mapper=new ObjectMapper();
		
		res.setContentType("application/json");
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		mapper.writeValue(res.getOutputStream(), e);
		
		
		
	}
}
