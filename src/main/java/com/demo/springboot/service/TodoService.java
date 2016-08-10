package com.demo.springboot.service;

import java.util.List;

import com.demo.springboot.domain.Status;
import com.demo.springboot.domain.Todo;
import com.demo.springboot.domain.User;

public interface TodoService {
	
	public boolean addTask(Todo todo);
	
	public List<Todo> getTasksByUser(User user);
	
	public boolean updateTask(Status status, Long id);
	
	public boolean deleteTask(Long id);

}
