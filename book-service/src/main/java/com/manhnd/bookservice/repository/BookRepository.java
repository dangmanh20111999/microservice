package com.manhnd.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manhnd.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
