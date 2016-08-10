package com.demo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.domain.User;
import com.demo.springboot.repository.UserRepository;
import com.demo.springboot.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		try{
			User registeredUser  = userRepository.save(user);
			return registeredUser;
		}catch(Exception ex){
			return null;
		}
	}

	@Override
	public User login(User user) {
		User dbUser = userRepository.findByName(user.getName());
		if(dbUser!=null && dbUser.getPassword().equals(user.getPassword()))
			return dbUser;
		else
			return null;
	}

}
