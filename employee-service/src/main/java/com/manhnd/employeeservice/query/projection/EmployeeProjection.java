package com.manhnd.employeeservice.query.projection;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manhnd.employeeservice.command.data.Employee;
import com.manhnd.employeeservice.command.data.EmployeeRepository;
import com.manhnd.employeeservice.query.model.EmployeeResponseCommonModel;
import com.manhnd.employeeservice.query.model.EmployeeResponseModel;
import com.manhnd.employeeservice.query.queries.GetAllEmployeeQuery;
import com.manhnd.employeeservice.query.queries.GetDetailsEmployeeQuery;
import com.manhnd.employeeservice.query.queries.GetEmployeesQuery;

@Component
public class EmployeeProjection {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@QueryHandler
	public EmployeeResponseModel handle(GetEmployeesQuery getEmployeeQuery) {
		EmployeeResponseModel model = new EmployeeResponseModel();
		Employee employee = employeeRepository.getById(getEmployeeQuery.getEmployeeId());
		BeanUtils.copyProperties(model, employee);
		return model;
	}
	
	@QueryHandler
	public EmployeeResponseCommonModel handle(GetDetailsEmployeeQuery getDetailsEmployeeQuery) {
		EmployeeResponseCommonModel model = new EmployeeResponseCommonModel();
		Employee employee = employeeRepository.getById(getDetailsEmployeeQuery.getEmployeeId());
		BeanUtils.copyProperties(model, employee);
		return model;
	}
	
	@QueryHandler
	public List<EmployeeResponseModel> handle(GetAllEmployeeQuery getAllEmployeeQuery) {
		List<EmployeeResponseModel> listModel = new ArrayList<>();
		List<Employee> listEmployee = employeeRepository.findAll();
		listEmployee.stream().forEach(s -> {
			EmployeeResponseModel model = new EmployeeResponseModel();
			BeanUtils.copyProperties(s, model);
			listModel.add(model);
		});
		return listModel;
	}
}
