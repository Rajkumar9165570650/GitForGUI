package com.raj.service;

import java.util.List;

import com.raj.dto.StudentDto;

public interface IStudent {
	
	String addStudent(StudentDto dto);
	StudentDto findByIdStudent(String id);
	List<StudentDto> findAllStudent(); 
	String parcialUpdateStudent(String id,String className);
	String fullUpdateStudent( StudentDto dto );
	String deleteByIdStudent(String id);
	

}
