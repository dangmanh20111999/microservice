package com.manhnd.bookservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhnd.bookservice.repository.BookRepository;
import com.manhnd.bookservice.response.ResultResponse;
import com.manhnd.bookservice.service.BookService;
import com.manhnd.bookservice.util.Convertor;
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public ResultResponse getAllBooks() {
		ResultResponse result = new ResultResponse();
		try {
			result.setData(Convertor.objectMapperConvertObjectToString(bookRepo.getAllBooks()));
			result.setCode("00");
			result.setMessage("SS");
			result.setTotal(bookRepo.getAllBooks().size());
		}catch (Exception ex) {
			
		}
		return result;
	}

}
