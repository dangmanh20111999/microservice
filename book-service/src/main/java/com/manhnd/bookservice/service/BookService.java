package com.manhnd.bookservice.service;

import com.manhnd.bookservice.response.ResultResponse;

public interface BookService {

	ResultResponse getAllBooks();
	
	ResultResponse getBookById(String id);
}
