package com.manhnd.employeeservice.command.model;

import lombok.Data;

@Data
public class EmployeeDeleteEvent {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
}
