package com.leucine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leucine.dto.StudentDTO;
import com.leucine.dto.StudentProfileDTO;
import com.leucine.dto.StudentUpdateDTO;
import com.leucine.exception.CourseException;
import com.leucine.exception.LoginException;
import com.leucine.exception.StudentException;
import com.leucine.modal.Course;
import com.leucine.modal.Enrollment;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.StudentProfile;
import com.leucine.modal.Users;
import com.leucine.repository.CourseRepository;
import com.leucine.repository.DepartmentRepository;
import com.leucine.repository.EnrollmentRepository;
import com.leucine.repository.StudentProfileRepository;
import com.leucine.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class StudentProfileServiceImpl implements StudentProfileService{
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private StudentProfileRepository studentProfileRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Override
	@Transactional
	public StudentProfile createStudent(Integer id,StudentProfileDTO studentProfileDto){
		
		Course course=courseRepository.findById(id)
				.orElseThrow(()-> new CourseException("Inviled course id."));
				
				
		Users user=new Users();
		user.setPassword(passwordEncoder.encode(studentProfileDto.getPassword()));
		user.setEmail(studentProfileDto.getEmail());
		user.setName(studentProfileDto.getName());
		user.setPhone(studentProfileDto.getPhone());
		user.setRole(studentProfileDto.getRole());
		user.setUsername(studentProfileDto.getUsername());
		
		
		StudentProfile studentProfile=new StudentProfile();
		studentProfile.setYear(studentProfileDto.getYear());
		studentProfile.setPhoto(studentProfileDto.getPhoto());
		studentProfile.setDepartment(course.getDepartment());
		studentProfile.setUser(user);
		
		user.setStudentProfile(studentProfile);
		
		List<Enrollment> list= new ArrayList<>();
		Enrollment enrollment= new Enrollment();
		enrollment.setCourse(course);
		enrollment.setStudent(studentProfile);	
		studentProfile.setEnrollments(list);
		userRepository.save(user);
		
		courseRepository.save(course);
		StudentProfile sp=studentProfileRepository.save(studentProfile);
		enrollmentRepository.save(enrollment);
		return sp;
		 
	}

	@Override
	public StudentDTO ownProfile() throws LoginException {
		
		//checking user exist or not
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		StudentDTO st= new StudentDTO();
		st.setStudentProfile(user.getStudentProfile());
		List<Enrollment>enrollments= user.getStudentProfile().getEnrollments();
		List<Course> courses=new ArrayList<>();
		for(Enrollment en:enrollments) {
			courses.add(en.getCourse());
		}
		st.setCourses(courses);
		return st;
	
	}


	@Override
	public List<Users> findByName(String name) {

		List<Users> users=userRepository.findByNameContaining(name);
		if(users.isEmpty()) {
			throw new StudentException("Empty list");
		}
		return users;
	}

	@Override
	public List<Users> findByYear(String Year) {

		List<Users> dtos=studentProfileRepository.findByYearContaining(Year)
				.stream().map(s->s.getUser()).collect(Collectors.toList());
		if(dtos.isEmpty()) {
			throw new StudentException("Empty list");
		}
		
		return dtos;
		
	}

	@Override
	public List<Users> findByDEpartment(String department) {
		
		List<Users> dtos=studentProfileRepository.findByDepartmentNameContaining(department)
				.stream().map(s->s.getUser()).collect(Collectors.toList());
		if(dtos.isEmpty()) {
			throw new StudentException("Empty list");
		}
		
		return dtos;
	}

	@Override
	public List<FacultyProfile> facultyProfiles() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		List<FacultyProfile> flist=new ArrayList<>();
		
		List<Enrollment> elist=user.getStudentProfile().getEnrollments();
		for(Enrollment e:elist) {
			flist.add(e.getCourse().getFaculty());
		}
		
		return flist;
	}

	@Override
	public Users updateStudentProfile(StudentUpdateDTO updateDTO) {
		Users user=userRepository.findById(updateDTO.getId())
				.orElseThrow(()-> new StudentException("Inviled student id."));
		user.setEmail(updateDTO.getEmail());
		user.setPassword(updateDTO.getPassword());
		user.setPhone(updateDTO.getPhone());
		user.setName(updateDTO.getName());
		user.getStudentProfile().setYear(updateDTO.getYear());
		user.getStudentProfile().setPhoto(updateDTO.getPhoto());
		userRepository.save(user);
		return user;
	}

	@Override
	public String removeStudent(String username) {

		Users user=userRepository.findByUsername(username)
				.orElseThrow(()-> new StudentException("Inviled student id."));
		userRepository.delete(user);
		return "Deleted";
		
	}

	

}
