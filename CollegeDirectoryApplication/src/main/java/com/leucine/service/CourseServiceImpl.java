package com.leucine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.exception.CourseException;
import com.leucine.exception.DepartmentException;
import com.leucine.exception.FacultyException;
import com.leucine.modal.Course;
import com.leucine.modal.Department;
import com.leucine.modal.FacultyProfile;
import com.leucine.repository.CourseRepository;
import com.leucine.repository.DepartmentRepository;
import com.leucine.repository.FacultyProfileRepository;
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private FacultyProfileRepository facultyProfileRepository;
	
	@Override
	public Course createCourse(Integer id,Integer fecultyId,Course course) {
		
		Department department=departmentRepository.findById(id)
				.orElseThrow(()-> new DepartmentException("Inviled department id : "+id));
		
		FacultyProfile facultyProfile=facultyProfileRepository.findById(fecultyId)
				.orElseThrow(()-> new FacultyException("Inviled faculty id : "+fecultyId));
		
		List<Course> courses=new ArrayList<>();
		course.setDepartment(department);
		course.setFaculty(facultyProfile);
		courses.add(course);
		facultyProfile.setCourses(courses);
		Course savedCourse=courseRepository.save(course);
		return savedCourse;
		
	}

	@Override
	public Course updateCourse(Course course) throws CourseException {
		// TODO Auto-generated method stub
		return null;
	}

}
