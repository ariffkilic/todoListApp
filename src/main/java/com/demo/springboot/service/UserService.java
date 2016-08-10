package com.demo.springboot.service;

import com.demo.springboot.domain.User;
import com.demo.springboot.service.response.UserResponse;

public interface UserService {
	
	public UserResponse registerUser(User user);
	
	public UserResponse login(User user);

}
