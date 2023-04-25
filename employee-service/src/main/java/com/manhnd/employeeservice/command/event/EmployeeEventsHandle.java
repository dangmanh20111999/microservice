package com.manhnd.employeeservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manhnd.employeeservice.command.data.Employee;
import com.manhnd.employeeservice.command.data.EmployeeRepository;

@Component
public class EmployeeEventsHandle {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@EventHandler
	public void on(EmployeeCreateEvent event) {
		Employee entity = new Employee();
		BeanUtils.copyProperties(event, entity);
		employeeRepository.save(entity);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(EmployeeUpdateEvent event) {
		Employee entity = employeeRepository.getById(event.getEmployeeId());
		entity.setFirstName(event.getFirstName());
		entity.setLastName(event.getLastName());
		entity.setKin(event.getKin());
		entity.setDisciplined(event.getIsDisciplined());
		employeeRepository.save(entity);
	}
	
	@EventHandler
	public void on(EmployeeDeleteEvent event) {
		employeeRepository.deleteById(event.getEmployeeId());
	}
}
