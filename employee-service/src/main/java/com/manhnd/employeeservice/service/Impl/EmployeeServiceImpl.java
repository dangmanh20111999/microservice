package com.manhnd.employeeservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhnd.employeeservice.model.Employee;
import com.manhnd.employeeservice.repository.EmployeeRepository;
import com.manhnd.employeeservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

}
