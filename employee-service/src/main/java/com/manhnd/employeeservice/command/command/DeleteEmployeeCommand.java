package com.manhnd.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class DeleteEmployeeCommand {

	@TargetAggregateIdentifier
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
	public DeleteEmployeeCommand(String employeeId) {
		super();
		this.employeeId = employeeId;
	}
}
