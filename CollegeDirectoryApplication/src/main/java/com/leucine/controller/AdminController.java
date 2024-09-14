package com.leucine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.dto.AdministratorProfileDTO;
import com.leucine.dto.FacultyProfileDTO;
import com.leucine.dto.StudentProfileDTO;
import com.leucine.dto.StudentUpdateDTO;
import com.leucine.modal.AdministratorProfile;
import com.leucine.modal.Course;
import com.leucine.modal.Department;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.StudentProfile;
import com.leucine.modal.Users;
import com.leucine.service.AdministratorProfileService;
import com.leucine.service.CourseService;
import com.leucine.service.DepartmentService;
import com.leucine.service.FacultyProfileService;
import com.leucine.service.StudentProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private FacultyProfileService facultyProfileService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentProfileService studentProfileService;
	
	@Autowired
	private AdministratorProfileService administratorProfileService;
	
	@PostMapping("/department")
	public ResponseEntity<Department> createDepartmentController(@Valid @RequestBody Department department){
		Department us=departmentService.createDepartment(department);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	@PostMapping("/course/{cid}/{fid}")
	public ResponseEntity<Course> createCourseController(@Valid @PathVariable("cid") Integer cid,
			@Valid @PathVariable("fid") Integer fid,
			@Valid @RequestBody Course course){
		Course us=courseService.createCourse(cid,fid,course);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/user/{id}")
	public ResponseEntity<AdministratorProfile> createAdministratorController(@Valid @PathVariable("id") Integer id,
			@Valid @RequestBody AdministratorProfileDTO administratorProfileDTO){
		AdministratorProfile us=administratorProfileService.createAdministrator(id, administratorProfileDTO);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	@PostMapping("/student/{id}")
	public ResponseEntity<StudentProfile> createStudentController(@Valid @PathVariable("id") Integer id,
			@Valid @RequestBody StudentProfileDTO studentProfileDTO){
		StudentProfile us=studentProfileService.createStudent(id, studentProfileDTO);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	@PostMapping("/faculty/department/{id}")
	public ResponseEntity<FacultyProfile> createFacultyController(@Valid @PathVariable("id") Integer id,
			@Valid @RequestBody FacultyProfileDTO facultyProfileDTO){
		FacultyProfile us=facultyProfileService.createFaculty(id,facultyProfileDTO);
		return new ResponseEntity<>(us,HttpStatus.CREATED);
	}
	
	@PatchMapping("/student/update/{id}")
	public ResponseEntity<Users> updateStudentController(@Valid @PathVariable("id") Integer id,
			@Valid @RequestBody StudentUpdateDTO studentUpdateDTO){
		Users us=studentProfileService.updateStudentProfile(studentUpdateDTO);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@DeleteMapping("/student/removestu/{id}")
	public ResponseEntity<String> removeStudentController(@Valid @PathVariable("id") String id){
		String us=studentProfileService.removeStudent(id);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
	@DeleteMapping("/removefac/{id}")
	public ResponseEntity<String> removeFacultyController(@Valid @PathVariable("id") String id){
		String us=facultyProfileService.removeFaculty(id);
		return new ResponseEntity<>(us,HttpStatus.OK);
	}
	
}
