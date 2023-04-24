package com.manhnd.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class CreateEmployeeCommand {

	@TargetAggregateIdentifier
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
	public CreateEmployeeCommand(String employeeId, String firstName, String lastName, String kin,
			boolean isDisciplined) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.kin = kin;
		this.isDisciplined = isDisciplined;
	}
}
