package com.manhnd.employeeservice.command.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {

	@Id
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private boolean isDisciplined;
	
	
}
