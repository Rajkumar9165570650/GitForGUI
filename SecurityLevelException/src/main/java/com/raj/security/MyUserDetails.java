package com.raj.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.raj.entity.Student;
import com.raj.exception.StudentResourseNotFound;
import com.raj.repository.StudentRepository;

@Component
public class MyUserDetails implements UserDetailsService {
	
	@Autowired
	private StudentRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		   Student student = repo.findById(id).orElseThrow(()->new StudentResourseNotFound("Id is not Avaiable"));
		
		UserDetails userDetails = User.withUsername(student.getId())
		                         .password(student.getPassword())
		                         .roles(student.getRole())
		                         .build();
		
		return userDetails;
	}
}
