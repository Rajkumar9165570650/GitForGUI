package com.raj.exception;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class AccessDenidedException  implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ErrorPage e=new ErrorPage("You cant access this ",HttpStatus.FORBIDDEN.value(),LocalTime.now());
		
		ObjectMapper mapper=new ObjectMapper();
		response.setContentType("application/json");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		
		
		mapper.writeValue(response.getOutputStream(), e);
		
	}

}
