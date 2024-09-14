package com.leucine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.dto.FacultyProfileUpdateDTO;
import com.leucine.dto.StudentFacultyDTO;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.Users;
import com.leucine.service.FacultyProfileService;
import com.leucine.service.StudentProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
	
	@Autowired
	private FacultyProfileService facultyProfileService;
	
	
	@Autowired
	private StudentProfileService studentProfileService;
	
	
	
	@GetMapping("/facultyprofiles")
	public ResponseEntity<List<FacultyProfile>> facultyProfilesController(){
		List<FacultyProfile> us=studentProfileService.facultyProfiles();
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<Users> ownProfileController(){
		Users us=facultyProfileService.ownProfile();
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@GetMapping("/studentfacultylist")
	public ResponseEntity<List<StudentFacultyDTO>> studentFacultyListController(){
		List<StudentFacultyDTO> us=facultyProfileService.studentFacultyList();
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@PatchMapping("/faculty/update")
	public ResponseEntity<FacultyProfile> uodatProfileController(@Valid @RequestBody FacultyProfileUpdateDTO dto){
		FacultyProfile us=facultyProfileService.uodatProfile(dto);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	
}
