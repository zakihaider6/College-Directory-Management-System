package com.leucine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leucine.dto.AdministratorProfileDTO;
import com.leucine.exception.DepartmentException;
import com.leucine.modal.AdministratorProfile;
import com.leucine.modal.Department;
import com.leucine.modal.Users;
import com.leucine.repository.AdministratorProfileRepository;
import com.leucine.repository.DepartmentRepository;
import com.leucine.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService{
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private AdministratorProfileRepository administratorProfileRepository;

	@Override
	@Transactional
	public AdministratorProfile createAdministrator(Integer id,AdministratorProfileDTO administratorProfileDTO){
		
		Department department=departmentRepository.findById(id)
				.orElseThrow(()-> new DepartmentException("Inviled department id."));
		
		Users user=new Users();
		user.setPassword(passwordEncoder.encode(administratorProfileDTO.getPassword()));
		user.setEmail(administratorProfileDTO.getEmail());
		user.setName(administratorProfileDTO.getName());
		user.setPhone(administratorProfileDTO.getPhone());
		user.setRole(administratorProfileDTO.getRole());
		user.setUsername(administratorProfileDTO.getUsername());
		
		
		AdministratorProfile administratorProfile=new AdministratorProfile();
		administratorProfile.setPhoto(administratorProfileDTO.getPhoto());
		administratorProfile.setDepartment(department);
		administratorProfile.setUser(user);
		user.setAdministratorProfile(administratorProfile);
		userRepository.save(user);
		departmentRepository.save(department);
		return administratorProfileRepository.save(administratorProfile);
		 
	}


	

}
