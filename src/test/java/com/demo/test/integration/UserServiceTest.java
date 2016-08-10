package com.demo.test.integration;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.springboot.SpringDemoApplication;
import com.demo.springboot.domain.User;
import com.demo.springboot.repository.UserRepository;
import com.demo.springboot.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDemoApplication.class)
@WebIntegrationTest
public class UserServiceTest {
	
	public static final String TEST_NAME = "TestName";
	public static final String TEST_PASSWORD = "Test1234";
	public static final String TEST_DEPARTMENT = "TestDeparment";
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void testRegisterUser(){
		User newUser = new User();
		newUser.setName(TEST_NAME);
		newUser.setPassword(TEST_PASSWORD);
		newUser.setDepartment(TEST_DEPARTMENT);
		
		userService.registerUser(newUser);
		assertNotNull(newUser.getId());
		
		User userFromDB = userRepository.findOne(newUser.getId());
		assertEquals(newUser.getName(), userFromDB.getName());
		assertEquals(newUser.getPassword(), userFromDB.getPassword());
		assertEquals(newUser.getDepartment(), userFromDB.getDepartment());
		
		userRepository.delete(newUser.getId());
	}
	
	@Test
	public void testLogin(){
		User newUser = new User();
		newUser.setName(TEST_NAME);
		newUser.setPassword(TEST_PASSWORD);
		newUser.setDepartment(TEST_DEPARTMENT);
		
		userService.registerUser(newUser);
		assertNotNull(newUser.getId());
		
		User loginedUser = userService.login(newUser);
		
		assertEquals(newUser.getName(), loginedUser.getName());
		assertEquals(newUser.getPassword(), loginedUser.getPassword());
		assertEquals(newUser.getDepartment(), loginedUser.getDepartment());
		
		userRepository.delete(newUser.getId());
	}

}
