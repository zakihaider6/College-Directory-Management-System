package com.leucine.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateDTO {
	
	private Integer id;
	private String name;
	private String photo;
	
	@Column(unique = true)
	@Email(message = "Email should be in right format")
	private String email;
	@Column(unique = true)
	@Pattern(regexp = "^[6-9]\\d{9}$",message= "Mobile Number Should be of 10 digits and starts with 6-9")
	private String phone;
	private String year;
	private String password;

}
