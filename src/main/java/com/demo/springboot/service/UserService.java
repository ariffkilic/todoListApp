package com.demo.springboot.service;

import com.demo.springboot.domain.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User login(User user);

}
