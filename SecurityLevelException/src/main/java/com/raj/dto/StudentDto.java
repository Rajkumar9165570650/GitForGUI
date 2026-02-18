package com.raj.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

	private String id;
	@NotBlank(message = "Name Not Be Blank")
	private String name;
	@NotBlank(message = "Address not be Blank")
	private String address;
	@Positive(message = "Value Not be Nagative")
	@Min(0)
	@Max(100)
	private Integer age;
	
	@NotBlank(message = "ClassName not be Blank")
	private String className;
	
	@NotBlank(message = "Password must be their")
	private String   password;
	
}
