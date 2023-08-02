package com.manhnd.employeeservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.manhnd.employeeservice.repository.EmployeeRepository;
import com.manhnd.employeeservice.response.BookDTO;
import com.manhnd.employeeservice.response.BookEmpResponse;
import com.manhnd.employeeservice.response.ResultResponse;
import com.manhnd.employeeservice.service.EmployeeService;
import com.manhnd.employeeservice.util.Convertor;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
    private Environment env;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public BookEmpResponse getAllEmployees() {
		ResultResponse response = restTemplate.getForObject(env.getProperty("bookService.getAllBooks"), ResultResponse.class);
		List<BookDTO> listBooks = new ArrayList<BookDTO>();
		listBooks = Convertor.objectMapperConvertStringToList(response.getData().toString(), BookDTO[].class);
		BookEmpResponse resultData = new BookEmpResponse();
		resultData.setBookDto(listBooks);
		resultData.setEmployeeDto(employeeRepo.findAll());
		return resultData;
	}

}
