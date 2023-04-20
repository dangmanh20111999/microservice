package com.manhnd.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.manhnd.bookservice.command.command.CreateBookCommand;
import com.manhnd.bookservice.command.event.BookCreateEvent;

@Aggregate
public class BookAggregate {

	@AggregateIdentifier
	private String bookId;
	private String name;
	private String author;
	private Boolean isReady;
	
	public BookAggregate() {
		
	}
	
	@CommandHandler
	public BookAggregate(CreateBookCommand createBookCommand) {
		BookCreateEvent bookCreateEvent = new BookCreateEvent();
		BeanUtils.copyProperties(createBookCommand, bookCreateEvent);
		AggregateLifecycle.apply(bookCreateEvent);
	}
	@EventSourcingHandler
	public void on(BookCreateEvent event) {
		this.bookId = event.getBookId();
		this.author = event.getAuthor();
		this.name = event.getName();
		this.isReady = event.getIsReady();
	}
}
