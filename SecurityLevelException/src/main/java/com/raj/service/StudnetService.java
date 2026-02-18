package com.raj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raj.dto.StudentDto;
import com.raj.entity.Student;
import com.raj.exception.StudentResourseNotFound;
import com.raj.mapper.StudentMapper;
import com.raj.repository.StudentRepository;

@Service
public class StudnetService implements IStudent {
	
	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private StudentMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public String addStudent(StudentDto dto) {
		Student stud = mapper.toEntity(dto);
		
		stud.setRole("USER");
		stud.setPassword(encoder.encode(dto.getPassword()));
		Student save = repo.save(stud);
		return save.getName()+" is save";
	}

	@Override
	public StudentDto findByIdStudent(String id) {
		Student student = repo.findById(id).orElseThrow(()-> new StudentResourseNotFound("id is not found"));
		StudentDto dto = mapper.toDto(student);
		return dto;
	}

	@Override
	public List<StudentDto> findAllStudent() {
		List<Student> all = repo.findAll();
		List<StudentDto> dtoList = mapper.toDtoList(all);
		return dtoList;
	}

	@Override
	public String parcialUpdateStudent(String id, String className) {
		Student student = repo.findById(id).orElseThrow(()-> new StudentResourseNotFound("id is not found"));
		student.setClassName(className);
		Student save = repo.save(student);
		return save.getName()+" class name is Update..";
	}

	@Override
	public String fullUpdateStudent(StudentDto dto) {
		Student student = repo.findById(dto.getId()).orElseThrow(()-> new StudentResourseNotFound("id is not found"));
		Student save = repo.save(student);
		return save.getName()+" is uodate";
	}

	@Override
	public String deleteByIdStudent(String id) {
		Student student = repo.findById(id).orElseThrow(()-> new StudentResourseNotFound("id is not found"));
		repo.delete(student);
		return student.getName()+" Data is delete..";
	}

}
