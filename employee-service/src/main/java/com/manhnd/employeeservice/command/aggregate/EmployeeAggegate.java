package com.manhnd.employeeservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.manhnd.employeeservice.command.command.CreateEmployeeCommand;
import com.manhnd.employeeservice.command.command.DeleteEmployeeCommand;
import com.manhnd.employeeservice.command.command.UpdateEmployeeCommand;
import com.manhnd.employeeservice.command.event.EmployeeCreateEvent;
import com.manhnd.employeeservice.command.event.EmployeeDeleteEvent;
import com.manhnd.employeeservice.command.event.EmployeeUpdateEvent;

@Aggregate
public class EmployeeAggegate {

	@AggregateIdentifier
	private String employeeId;
	private String firstName;
	private String lastName;
	private String kin;
	private Boolean isDisciplined;
	public EmployeeAggegate() {
		super();
	}
	@CommandHandler
	public EmployeeAggegate(CreateEmployeeCommand createEmployeeCommand) {
		EmployeeCreateEvent event = new EmployeeCreateEvent();
		BeanUtils.copyProperties(createEmployeeCommand, event);
		AggregateLifecycle.apply(event);
	}
	
	@CommandHandler
	public void handle(UpdateEmployeeCommand updateEmployeeCommand) {
		EmployeeUpdateEvent event = new EmployeeUpdateEvent();
		BeanUtils.copyProperties(updateEmployeeCommand, event);
		AggregateLifecycle.apply(event);
	}
	
	@CommandHandler
	public void handle(DeleteEmployeeCommand deleteEmployeeCommand) {
		EmployeeDeleteEvent event = new EmployeeDeleteEvent();
		BeanUtils.copyProperties(deleteEmployeeCommand, event);
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(EmployeeCreateEvent event) {
		this.employeeId = event.getEmployeeId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.isDisciplined = event.getIsDisciplined();
		this.kin = event.getKin();
	}
	@EventSourcingHandler
	public void on(EmployeeUpdateEvent event) {
		this.employeeId = event.getEmployeeId();
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.isDisciplined = event.getIsDisciplined();
		this.kin = event.getKin();
	}
	@EventSourcingHandler
	public void on(EmployeeDeleteEvent event) {
		this.employeeId = event.getEmployeeId();
	}
}
