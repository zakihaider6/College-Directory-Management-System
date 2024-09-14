package com.leucine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.modal.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	List<Department> findByName(String department);
}
