package com.leucine.service;

import com.leucine.exception.CourseException;
import com.leucine.exception.DepartmentException;
import com.leucine.modal.Course;

public interface CourseService {
	
	public Course createCourse(Integer deptId,Integer fecultyId,Course course)throws DepartmentException;
	public Course updateCourse(Course course)throws CourseException;

}
