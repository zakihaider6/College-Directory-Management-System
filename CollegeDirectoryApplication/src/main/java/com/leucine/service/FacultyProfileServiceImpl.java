package com.leucine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leucine.dto.FacultyProfileDTO;
import com.leucine.dto.FacultyProfileUpdateDTO;
import com.leucine.dto.StudentFacultyDTO;
import com.leucine.exception.DepartmentException;
import com.leucine.exception.LoginException;
import com.leucine.exception.StudentException;
import com.leucine.modal.Department;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.Users;
import com.leucine.repository.DepartmentRepository;
import com.leucine.repository.FacultyProfileRepository;
import com.leucine.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class FacultyProfileServiceImpl implements FacultyProfileService{
	
	@Autowired
	private FacultyProfileRepository facultyProfileRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	@Transactional
	public FacultyProfile createFaculty(Integer id,FacultyProfileDTO facultyProfileDTO)throws DepartmentException {
		
		Department department=departmentRepository.findById(id)
				.orElseThrow(()-> new DepartmentException("Inviled department id."));
		
		Users user=new Users();
		user.setPassword(passwordEncoder.encode(facultyProfileDTO.getPassword()));
		user.setEmail(facultyProfileDTO.getEmail());
		user.setName(facultyProfileDTO.getName());
		user.setPhone(facultyProfileDTO.getPhone());
		user.setRole(facultyProfileDTO.getRole());
		user.setUsername(facultyProfileDTO.getUsername());
		
		
		FacultyProfile facultyProfile=new FacultyProfile();
		facultyProfile.setOffice_hours(facultyProfileDTO.getOffice_hours());
		facultyProfile.setPhoto(facultyProfileDTO.getPhoto());
		facultyProfile.setDepartment(department);
		facultyProfile.setUser(user);
		user.setFacultyProfile(facultyProfile);
		departmentRepository.save(department);
		userRepository.save(user);
		return facultyProfileRepository.save(facultyProfile);
		 
	}

	@Override
	public Users ownProfile() throws LoginException {
		//checking user exist or not
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		return user;
	}

	@Override
	public List<StudentFacultyDTO> studentFacultyList() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		
		List<Users> userList =userRepository.findAll().stream()
		.filter(s->s.getFacultyProfile()==user.getFacultyProfile()).toList();
		
		List<StudentFacultyDTO> studentFacultyDTOs= new ArrayList<>();
		for(Users u:userList) {
			StudentFacultyDTO dto= new StudentFacultyDTO();
			dto.setEmail(u.getEmail());
			dto.setName(u.getName());
			dto.setPhone(u.getPhone());
			dto.setPhoto(u.getStudentProfile().getPhoto());
			studentFacultyDTOs.add(dto);
		}
		return studentFacultyDTOs;
		
	}

	@Override
	public FacultyProfile uodatProfile(FacultyProfileUpdateDTO profileUpdateDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userRepository.findByUsername(authentication.getName())
				.orElseThrow(() -> new LoginException("Please Login First"));
		if(profileUpdateDTO.getEmail()!=null) {
			user.setEmail(profileUpdateDTO.getEmail());		}
		if(profileUpdateDTO.getOffice_hours()!=null) {
			user.getFacultyProfile().setOffice_hours(profileUpdateDTO.getOffice_hours());
		}
		if(profileUpdateDTO.getPhone()!=null) {
			user.setPhone(profileUpdateDTO.getPhone());
		}
		userRepository.save(user);
		return user.getFacultyProfile();
	}

	@Override
	public String removeFaculty(String username) {
		Users user=userRepository.findByUsername(username)
				.orElseThrow(()-> new StudentException("Inviled student id."));
		userRepository.delete(user);
		return "Deleted";
	}

}
