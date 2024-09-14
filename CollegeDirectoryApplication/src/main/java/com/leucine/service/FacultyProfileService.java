package com.leucine.service;

import java.util.List;

import com.leucine.dto.FacultyProfileDTO;
import com.leucine.dto.FacultyProfileUpdateDTO;
import com.leucine.dto.StudentFacultyDTO;
import com.leucine.exception.DepartmentException;
import com.leucine.exception.LoginException;
import com.leucine.modal.FacultyProfile;
import com.leucine.modal.Users;

public interface FacultyProfileService {
	public FacultyProfile createFaculty(Integer id,FacultyProfileDTO facultyProfileDTO) throws DepartmentException;
	public Users ownProfile()throws LoginException;
	public List<StudentFacultyDTO> studentFacultyList();
	public FacultyProfile uodatProfile(FacultyProfileUpdateDTO profileUpdateDTO);
	public String removeFaculty(String username);
}
