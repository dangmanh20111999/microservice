package com.manhnd.employeeservice.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.employeeservice.query.model.EmployeeResponseModel;
import com.manhnd.employeeservice.query.queries.GetAllEmployeeQuery;
import com.manhnd.employeeservice.query.queries.GetEmployeesQuery;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {

	@Autowired
	private QueryGateway queryGateway;
	
	@GetMapping("/{employeeId}")
	public EmployeeResponseModel getEmployeeDetail(@PathVariable String employeeId) {
		GetEmployeesQuery getEmployeesQuery = new GetEmployeesQuery();
		getEmployeesQuery.setEmployeeId(employeeId);
		EmployeeResponseModel employeeResponseModel = queryGateway.query(getEmployeesQuery, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
		return employeeResponseModel;
	}
	
	@GetMapping
	public List<EmployeeResponseModel> getAllEmployee() {
		List<EmployeeResponseModel> list = queryGateway.query(new GetAllEmployeeQuery(), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
		return list;
	}
}