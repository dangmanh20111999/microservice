package com.manhnd.employeeservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {

	@Id
	private String ids;
	private String name;
	private int old;
	private String address;
}
