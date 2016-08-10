package com.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springboot.domain.User;
import com.demo.springboot.exception.TodoCreationException;
import com.demo.springboot.service.TodoService;
import com.demo.springboot.service.UserService;
import com.demo.springboot.service.response.UserResponse;

@Controller
public class RegisterController {
	
	public static final String USER_NAME_EXIST_ERROR = "User name is exist, please try with different username";
	
	@Autowired
	UserService userService;
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	TodoUtil todoUtil;
	
	@RequestMapping("/register")
	public ModelAndView toRegisterPage(){
		return new ModelAndView("register", "user", new User());
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user, HttpSession session) throws TodoCreationException{	
		UserResponse response = userService.registerUser(user);
		if(Boolean.TRUE.equals(response.getSuccess())){
			User loginedUser = response.getUser();
			session.setAttribute("loginedUser", loginedUser);
			return todoUtil.prepareModel(loginedUser);
		}else{
			throw new TodoCreationException(response.getErrorMessage());
		}
	}
	
	@ExceptionHandler(TodoCreationException.class)
	public ModelAndView handleSavingError(TodoCreationException ex){
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("user", new User());
		model.put("errorMessage", ex.getMessage());
		return new ModelAndView("register",model);
	}

}