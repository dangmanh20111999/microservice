package com.manhnd.employeeservice.service;

import com.manhnd.employeeservice.response.BookEmpResponse;

public interface EmployeeService {

	BookEmpResponse getAllEmployees();
	
	BookEmpResponse getEmployeeById(String id);
}
