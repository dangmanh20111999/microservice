package com.manhnd.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manhnd.employeeservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}
