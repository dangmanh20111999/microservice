package com.manhnd.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value = "/getAllEmployees")
	public ResponseEntity<?> getAllEmployees() {
		return ResponseEntity.ok().body(employeeService.getAllEmployees());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable String id){
		return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
	}
}
