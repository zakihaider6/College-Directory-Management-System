package com.leucine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.modal.Department;
import com.leucine.repository.DepartmentRepository;
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department createDepartment(Department department) {

		Department depart=departmentRepository.save(department);
		return depart;
		
	}

}
