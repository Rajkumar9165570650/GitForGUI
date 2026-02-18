package com.raj.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(StudentResourseNotFound.class)
	public ResponseEntity<ErrorPage> customException(StudentResourseNotFound ex){
		ErrorPage e=new ErrorPage(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),LocalTime.now());
		
		return new ResponseEntity<ErrorPage>(e, HttpStatus.OK);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorPage> internalException(Exception ex){
		ErrorPage e=new ErrorPage(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalTime.now());
		
		return new ResponseEntity<ErrorPage>(e, HttpStatus.OK);
	}

}
