package com.manhnd.bookservice.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.bookservice.command.command.CreateBookCommand;
import com.manhnd.bookservice.command.command.DeleteBookCommand;
import com.manhnd.bookservice.command.command.UpdateBookCommand;
import com.manhnd.bookservice.command.model.BookRequestModel;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

	@Autowired(required = true)
	private CommandGateway commondGateway;
	
	@PostMapping
	public String addBook(@RequestBody BookRequestModel model) {
		CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), model.getName(), model.getAuthor(), true);
		commondGateway.sendAndWait(command); // gửi 1 command tới aggregate và đợi kết quả trả về
		return"Added Book";
	}
	
	@PutMapping
	public String updateBook(@RequestBody BookRequestModel model) {
		UpdateBookCommand command = new UpdateBookCommand(model.getBookId(), model.getName(), model.getAuthor(), model.getIsReady());
		commondGateway.sendAndWait(command);
		return "Updated Book";
	}
	
	@DeleteMapping("/{idBook}")
	public String deleteBook(@PathVariable String idBook) {
		DeleteBookCommand command = new DeleteBookCommand(idBook);
		commondGateway.sendAndWait(command);
		return "Deleted Book";
	}
}
