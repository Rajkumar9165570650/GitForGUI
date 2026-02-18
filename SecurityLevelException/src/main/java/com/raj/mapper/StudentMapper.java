package com.raj.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.raj.dto.StudentDto;
import com.raj.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	Student toEntity(StudentDto dto);
	StudentDto toDto(Student st);
	
	List<Student> toEntityList(List<StudentDto> dto);
	List<StudentDto> toDtoList(List<Student> entity);

}
