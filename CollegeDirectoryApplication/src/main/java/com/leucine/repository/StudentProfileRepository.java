package com.leucine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.modal.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer>{
	List<StudentProfile> findByYearContaining(String year);
	List<StudentProfile> findByDepartmentNameContaining(String departmentName);
}
