package com.raj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.raj.entity.Student;

@Repository
public interface StudentRepository   extends MongoRepository<Student, String>{
	Student findByName(String name);

}
