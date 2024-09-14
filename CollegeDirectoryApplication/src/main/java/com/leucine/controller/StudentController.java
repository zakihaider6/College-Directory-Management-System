package com.leucine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.dto.StudentDTO;
import com.leucine.dto.StudentProfileDTO;
import com.leucine.modal.StudentProfile;
import com.leucine.modal.Users;
import com.leucine.service.StudentProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentProfileService studentProfileService;

	
	@GetMapping("/studentprofile")
	public ResponseEntity<StudentDTO> studentProfileController(){
		StudentDTO us=studentProfileService.ownProfile();
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@GetMapping("/users/{name}")
	public ResponseEntity<List<Users>> findByNameController(@Valid @PathVariable("name") String name){
		List<Users> us=studentProfileService.findByName(name);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@GetMapping("/users/year/{year}")
	public ResponseEntity<List<Users>> findByyearProfileController(@Valid @PathVariable("year") String year){
		List<Users> us=studentProfileService.findByYear(year);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@GetMapping("/users/department/{department}")
	public ResponseEntity<List<Users>> findByDepartmentController(@Valid @PathVariable("department") String department){
		List<Users> us=studentProfileService.findByDEpartment(department);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	


}
