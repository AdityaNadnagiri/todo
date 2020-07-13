package com.rest.react.webservice.todo.todos;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.react.webservice.todo.entity.Todo;
import com.rest.react.webservice.todo.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {

	@Autowired
	private TodoService todoService;

	@GetMapping(path = "/users/{userName}/todos")
	public List<Todo> getAllToddos(@PathVariable String userName) {

		return todoService.findAllTodos(userName);

	}

	@GetMapping(path = "/users/{userName}/todos/{id}")
	public ResponseEntity<Todo> getToddo(@PathVariable String userName, @PathVariable Long id) {

		return new ResponseEntity<Todo>(todoService.findTodo(id), HttpStatus.OK);

	}

	@PutMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String userName, @PathVariable Long id, @RequestBody Todo todo) {
		ResponseEntity<Todo> response = new ResponseEntity<Todo>(todo, HttpStatus.BAD_REQUEST);

		try {
			;
			response = new ResponseEntity<Todo>(todoService.save(todo), HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = new ResponseEntity<Todo>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;

	}

	@PostMapping(path = "/users/{username}/todos")
	public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
		ResponseEntity<Todo> response = new ResponseEntity<Todo>(todo, HttpStatus.BAD_REQUEST);

		try {
			Todo createdTodo = todoService.save(todo);
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

	@DeleteMapping(path = "/users/{userName}/todos/{id}")
	public ResponseEntity<Void> deleteByid(@PathVariable String userName, @PathVariable Long id) {

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
