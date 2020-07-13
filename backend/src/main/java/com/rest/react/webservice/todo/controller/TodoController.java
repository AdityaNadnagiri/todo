package com.rest.react.webservice.todo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.react.webservice.todo.entity.Todo;
import com.rest.react.webservice.todo.service.TodoService;
import com.rest.react.webservice.todo.todos.todoException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/jpa/users")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping(path = "/{username}/todos")
	public List<Todo> getAllToddos(@PathVariable String username) {

			throw new todoException("no message", HttpStatus.BAD_REQUEST);
		
		// return todoService.findAllTodos(username);

	}

	@GetMapping(path = "/{username}/todos/{id}")
	public ResponseEntity<Todo> getToddo(@PathVariable String username, @PathVariable long id) {
		
		ResponseEntity<Todo> response = new ResponseEntity<Todo>(HttpStatus.ACCEPTED);
		
		try {
			return new ResponseEntity<Todo>(todoService.findTodo(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return response;

	}

	@PutMapping(path = "/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id, @RequestBody Todo todo) {
		ResponseEntity<Todo> response = new ResponseEntity<Todo>(todo, HttpStatus.BAD_REQUEST);

		try {
			todo.setUsername(username);
			todo.setId(id);
			response = new ResponseEntity<Todo>(todoService.create(todo), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return response;

	}

	@PostMapping(path = "/{username}/todos")
	public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo) {
		ResponseEntity<Todo> response = new ResponseEntity<Todo>(todo, HttpStatus.BAD_REQUEST);

		try {
			todo.setUsername(username);
			Todo createdTodo = todoService.create(todo);
			// response = new ResponseEntity<Todo>(createdTodo, HttpStatus.OK);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(createdTodo.getId())
					.toUri();
			response = ResponseEntity.created(uri).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<Todo>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;

	}

	@DeleteMapping(path = "/{username}/todos/{id}")
	public ResponseEntity<Void> deleteByid(@PathVariable String username, @PathVariable Long id) {

		ResponseEntity<Void> response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		try {
			todoService.deleteTodos(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response = ResponseEntity.noContent().build();
		return response;
	}

}
