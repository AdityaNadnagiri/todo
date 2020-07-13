package com.rest.react.webservice.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rest.react.webservice.todo.entity.Todo;
import com.rest.react.webservice.todo.repository.impl.todoJpaRepositoryInterface;
import com.rest.react.webservice.todo.todos.todoException;

@Service
public class TodoService {

	@Autowired
	todoJpaRepositoryInterface todoJpaRepository;

	private static List<Todo> todos = new ArrayList<Todo>();
	private static long idCounter = 0;

	static {
		todos.add(new Todo(++idCounter, "Aditya", "Learn To Dance", new Date(), false));
		todos.add(new Todo(++idCounter, "Aditya", "Learn About Micro Services", new Date(), false));
		todos.add(new Todo(++idCounter, "Aditya", "Lear About Angular", new Date(), false));
	}

	public List<Todo> findAllTodos(String userName) {
		return todoJpaRepository.findAll();
	}

	public void deleteTodos(long id) {

		try {
			todoJpaRepository.deleteById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * private Todo findById(Long id) { Todo todoToReturn = null; for (Todo todo :
	 * todos) { if (todo.getId() == id) { todoToReturn = todo; } } return
	 * todoToReturn; }
	 */

	public Todo save(Todo todo) {
		if (todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		} else {
			deleteTodos((long) todo.getId());
			todos.add(todo);

		}
		return todo;
	}

	public Todo findTodo(long id) {

		Todo todo = todoJpaRepository.findById(id);

		if (todo == null) {
			throw new todoException("No Datas", HttpStatus.NOT_FOUND);
		}

		return todo;

	}

	public Todo create(Todo todo) {

		if (null != todo.getId()) {
			long id = todo.getId();
			Todo todoToUpdate = todoJpaRepository.findById(id);
			if (todoToUpdate == null) {
				throw new todoException("No Data");
			}
		}

		Todo savedTodo = todoJpaRepository.save(todo);

		return savedTodo;
	}

}
