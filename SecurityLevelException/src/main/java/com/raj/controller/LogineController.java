package com.raj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.dto.RequestAuthorize;
import com.raj.dto.ResponseAuthorize;
import com.raj.security.JWTUtils;

@RestController
@RequestMapping("/login-api")
public class LogineController {
	//this is Controller class
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JWTUtils utils;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseAuthorize> login(@RequestBody RequestAuthorize req){
		Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));
		
		String key = utils.generateKey(req.getUserName());
		return new ResponseEntity<ResponseAuthorize> (new ResponseAuthorize(key),HttpStatus.OK);	
	}
	

}
