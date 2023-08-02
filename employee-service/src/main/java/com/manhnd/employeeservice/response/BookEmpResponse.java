package com.manhnd.employeeservice.response;

import java.util.List;

import com.manhnd.employeeservice.model.Employee;

import lombok.Data;

@Data
public class BookEmpResponse {

	List<Employee> employeeDto;
	List<BookDTO> bookDto;
}
