package com.manhnd.employeeservice.query.queries;

import lombok.Data;

@Data
public class GetDetailsEmployeeQuery {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private Boolean isDisciplined;
}
