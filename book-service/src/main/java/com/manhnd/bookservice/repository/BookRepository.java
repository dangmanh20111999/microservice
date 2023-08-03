package com.manhnd.bookservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manhnd.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

	@Query(value = "SELECT * FROM BOOKS", nativeQuery = true)
	List<Book> getAllBooks();
	
	@Query(value = "SELECT * FROM BOOKS WHERE IDS = :id", nativeQuery = true)
	List<Book> getBookById(String id);
}
