package com.manhnd.bookservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manhnd.bookservice.command.data.Book;
import com.manhnd.bookservice.command.data.BookRepository;

@Component
public class BookEventsHandler {

	@Autowired
	private BookRepository bookRepository;
	
	@EventHandler
	public void on(BookCreateEvent event) {
		Book book = new Book();
		BeanUtils.copyProperties(event, book);
		bookRepository.save(book);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void on(BookUpdateEvent event) {
		Book book = bookRepository.getById(event.getBookId());
		book.setAuthor(event.getAuthor());
		book.setIsReady(event.getIsReady());
		book.setName(event.getName());
		bookRepository.save(book);
	}
	
	@EventHandler
	public void on(BookDeleteEvent event) {
		bookRepository.deleteById(event.getBookId());
	}
}
