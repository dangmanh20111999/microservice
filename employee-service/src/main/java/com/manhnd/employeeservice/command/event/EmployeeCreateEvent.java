package com.manhnd.employeeservice.command.event;

import lombok.Data;

@Data
public class EmployeeCreateEvent {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
}
