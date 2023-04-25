package com.manhnd.employeeservice.command.event;

import lombok.Data;

@Data
public class EmployeeDeleteEvent {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private Boolean isDisciplined;
}
