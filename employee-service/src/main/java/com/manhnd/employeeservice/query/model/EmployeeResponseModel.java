package com.manhnd.employeeservice.query.model;

import lombok.Data;

@Data
public class EmployeeResponseModel {
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
}
