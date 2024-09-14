package com.leucine.dto;

import java.util.List;

import com.leucine.modal.Course;
import com.leucine.modal.StudentProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
	private StudentProfile studentProfile;
	private List<Course> courses;

}
