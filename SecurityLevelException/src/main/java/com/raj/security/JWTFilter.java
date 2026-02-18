package com.raj.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
	
	    @Autowired
		private JWTUtils utils;
	    
	    @Autowired
	    private MyUserDetails details;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = req.getHeader("Authorization");
		
		if(header !=null && header.startsWith("Bearer ")) {
			
			String token = header.substring(7);
			String name = utils.readToken(token);
			
			UserDetails userDetails = details.loadUserByUsername(name);
			
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
			  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		}
		filterChain.doFilter(req, resp);
		
	}

}
