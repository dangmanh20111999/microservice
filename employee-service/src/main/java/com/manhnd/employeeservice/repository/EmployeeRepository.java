package com.manhnd.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manhnd.employeeservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

	@Query(value = "SELECT * FROM EMPLOYEES WHERE IDS = :id", nativeQuery = true)
	List<Employee> getEmployeeById(String id);
}
