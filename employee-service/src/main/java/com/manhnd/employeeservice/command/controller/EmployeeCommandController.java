package com.manhnd.employeeservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.employeeservice.command.command.CreateEmployeeCommand;
import com.manhnd.employeeservice.command.model.EmployeeRequestModel;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

	@Autowired
	private CommandGateway commandGateway;
	
	@PostMapping
	public String addEmployee(@RequestBody EmployeeRequestModel model) {
		CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), true);
		commandGateway.sendAndWait(command);
		return "Added Employee";
	}
}
