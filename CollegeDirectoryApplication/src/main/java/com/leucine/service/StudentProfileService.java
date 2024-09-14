package com.leucine.service;

import java.util.List;

import com.leucine.dto.StudentDTO;
import com.leucine.dto.StudentProfileDTO;
import com.leucine.dto.StudentUpdateDTO;
import com.leucine.exception.LoginException;
import com.leucine.exception.StudentException;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.StudentProfile;
import com.leucine.modal.Users;

public interface StudentProfileService {
	public StudentProfile createStudent(Integer id,StudentProfileDTO studentProfileDto);
	public StudentDTO ownProfile()throws LoginException;
	public List<Users> findByName(String name);
	public List<Users> findByYear(String year);
	public List<Users> findByDEpartment(String department);
	public List<FacultyProfile> facultyProfiles();
	
	public Users updateStudentProfile(StudentUpdateDTO updateDTO) throws StudentException;
	public String removeStudent(String username);
	
}
