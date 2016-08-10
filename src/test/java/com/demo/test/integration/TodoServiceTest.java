package com.demo.test.integration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.springboot.SpringDemoApplication;
import com.demo.springboot.domain.Status;
import com.demo.springboot.domain.Todo;
import com.demo.springboot.repository.TodoRepository;
import com.demo.springboot.service.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
@WebIntegrationTest
public class TodoServiceTest {
	
	public static final String TEST_NAME = "TestName";
	public static final String TEST_INFO = "TestInfo";
	
	@Autowired
	TodoService toDoService;
	
	@Autowired
	TodoRepository todoRepository;
	
	@Test
	public void testCreateTodo() {
		Todo newTodo = new Todo();
		newTodo.setName(TEST_NAME);
		newTodo.setInformation(TEST_INFO);
		boolean success = toDoService.addTask(newTodo);
		
		assertTrue(success);
		assertTrue(newTodo.getId()!=null);
		
		Todo todoFromDb = todoRepository.findOne(newTodo.getId());
		assertEquals(newTodo.getName(), todoFromDb.getName());
		assertEquals(newTodo.getInformation(), todoFromDb.getInformation());
		assertEquals(newTodo.getStatus(), todoFromDb.getStatus());
		
		toDoService.deleteTask(newTodo.getId());
	}
	
	@Test
	public void testDeleteTodo() {
		Todo newTodo = new Todo();
		newTodo.setName(TEST_NAME);
		newTodo.setInformation(TEST_INFO);
		toDoService.addTask(newTodo);
		
		assertFalse(newTodo.getId()==null);
		
		boolean success = toDoService.deleteTask(newTodo.getId());		
		assertTrue(success);
		
		Todo deletedDb = todoRepository.findOne(newTodo.getId());
		assertNull(deletedDb);
	}
	
	@Test
	public void testUpdateTodo() {	
		Todo newTodo = new Todo();
		newTodo.setName(TEST_NAME);
		newTodo.setInformation(TEST_INFO);
		toDoService.addTask(newTodo);
		
		assertFalse(newTodo.getId()==null);
		
		toDoService.updateTask(Status.IN_PROGRESS,newTodo.getId());
		
		Todo todoFrmDb = todoRepository.findOne(newTodo.getId());	
		assertTrue(Status.IN_PROGRESS.equals(todoFrmDb.getStatus()));
		
		boolean success = toDoService.deleteTask(todoFrmDb.getId());
		assertTrue(success);
	}
}
