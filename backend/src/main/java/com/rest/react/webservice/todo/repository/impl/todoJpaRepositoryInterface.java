package com.rest.react.webservice.todo.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.react.webservice.todo.entity.Todo;

@Repository
public interface todoJpaRepositoryInterface extends JpaRepository<Todo, Long> {

	public Todo findByUsername(String username);

	public Todo findById(long id);

}
