package com.manhnd.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.bookservice.response.ResultResponse;
import com.manhnd.bookservice.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<ResultResponse> getAllBooks() {
		ResultResponse response = new ResultResponse();
		response = bookService.getAllBooks();
		return ResponseEntity.ok().body(response);
	}
}
