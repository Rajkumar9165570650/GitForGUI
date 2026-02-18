package com.raj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raj.dto.StudentDto;
import com.raj.service.IStudent;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student-api")
public class StudentControler {
	
	@Autowired
	private IStudent service;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addStudent(@Valid @RequestBody StudentDto dto) {
		String student = service.addStudent(dto);
		return new ResponseEntity<String>(student,HttpStatus.CREATED);
	}

    @GetMapping("/findById/{id}")
	public ResponseEntity<StudentDto> findByIdStudent(@PathVariable String id) {
	   StudentDto byIdStudent = service.findByIdStudent(id);
    	return new ResponseEntity<>(byIdStudent,HttpStatus.OK);
	}

    @GetMapping("/findAll")
	public ResponseEntity<List<StudentDto>> findAllStudent() {
	
    	List<StudentDto> allStudent = service.findAllStudent();
    	return new ResponseEntity<>(allStudent,HttpStatus.OK);
	}

	@PatchMapping("/update/{id}/{className}")
	public ResponseEntity<String>  parcialUpdateStudent(@PathVariable String id,@PathVariable  String className) {
		String parcialUpdateStudent = service.parcialUpdateStudent(id, className);
		return new ResponseEntity<>(parcialUpdateStudent,HttpStatus.OK);
	}

	
	@PutMapping("/update")
	public ResponseEntity<String>  fullUpdateStudent(@RequestBody StudentDto dto) {
		String fullUpdateStudent = service.fullUpdateStudent(dto);
		
		return new ResponseEntity<>(fullUpdateStudent,HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>  deleteByIdStudent(@PathVariable String id) {
		String deleteByIdStudent = service.deleteByIdStudent(id);
		return new ResponseEntity<>(deleteByIdStudent,HttpStatus.OK);
	}


}
