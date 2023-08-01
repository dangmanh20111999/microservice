package com.manhnd.bookservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhnd.bookservice.model.Book;
import com.manhnd.bookservice.repository.BookRepository;
import com.manhnd.bookservice.service.BookService;
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepo;

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

}
