package com.demo.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.demo.springboot.domain.User;
import com.demo.springboot.repository.UserRepository;
import com.demo.springboot.service.UserService;
import com.demo.springboot.service.response.UserResponse;

@Service
public class UserServiceImpl implements UserService{
	
	public static final String USER_NOT_EXIST  = "There is no any user with this name";
	public static final String PASSWRD_WRONG   =  "Name and password is incompatible";
	public static final String USER_NAME_EXIST = "User name is exist, please try with different username";
	public static final String MISSING_PARAMETER = "Name and password are required";
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserResponse registerUser(User user) {	
		UserResponse response = new UserResponse();
		if(!validateInput(user)){
			response.setSuccess(false);
			response.setErrorMessage(MISSING_PARAMETER);
			return response;
		}
		
		try{
			User registeredUser  = userRepository.save(user);
			response.setUser(registeredUser);
			response.setSuccess(true);
		}catch(DataIntegrityViolationException ex){
			response.setSuccess(false);
			response.setErrorMessage(USER_NAME_EXIST);
		}catch(Exception ex){
			response.setSuccess(false);
			response.setErrorMessage(ex.getMessage());
		}
		return response;
	}

	@Override
	public UserResponse login(User user) {
		UserResponse response = new UserResponse();	
		if(!validateInput(user)){
			response.setSuccess(false);
			response.setErrorMessage(MISSING_PARAMETER);
			return response;
		}
		
		User dbUser = userRepository.findByName(user.getName());
		if(dbUser==null){
			response.setSuccess(false);
			response.setErrorMessage(USER_NOT_EXIST);
		}else if(!dbUser.getPassword().equals(user.getPassword())){
			response.setErrorMessage(PASSWRD_WRONG);
			response.setSuccess(false);
		}
		else{
			response.setUser(dbUser);
			response.setSuccess(true);
		}
		return response;
	}
	
	private boolean validateInput(User user) {
		if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword()))
			return false;
		else
			return true;
	}

}
