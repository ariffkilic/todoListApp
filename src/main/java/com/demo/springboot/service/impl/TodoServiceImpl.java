package com.demo.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.domain.Status;
import com.demo.springboot.domain.Todo;
import com.demo.springboot.domain.User;
import com.demo.springboot.repository.TodoRepository;
import com.demo.springboot.service.TodoService;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository taskRepository;

	@Override
	public boolean addTask(Todo newTodo) {
		newTodo.setStatus(Status.NOT_STARTED);
		taskRepository.save(newTodo);
		return true;
	}
	
	public List<Todo> getAllTasks(){
		
		return (List<Todo>) taskRepository.findAll();
	}

	@Override
	public boolean updateTask(Status status, Long id) {
		taskRepository.updateTaskStatus(status, id);
		return true;
	}

	@Override
	public List<Todo> getTasksByUser(User user) {
		List<Todo> listOfTodo =  taskRepository.listTaskByUser(user);
		return listOfTodo;
	}

	@Override
	public boolean deleteTask(Long id) {
		taskRepository.delete(id);
		return true;
	}

}
