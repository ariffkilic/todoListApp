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
import com.demo.springboot.exception.TodoLoginException;
import com.demo.springboot.service.TodoService;
import com.demo.springboot.service.UserService;

@Controller
public class HomeController {
	
	public static final String LOGIN_ERROR = "Name and password is incompatible!!";
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TodoUtil todoUtil;
	
	@RequestMapping(value = {"/","/home"})
	public ModelAndView getHomePage(){
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("user", new User());
		model.put("errorMessage", new String());
		return new ModelAndView("home",model);
	}
	
	@RequestMapping(value = {"/loginUser"} ,method=RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("user") User user, HttpSession session) throws TodoLoginException{
		User loginedUser = userService.login(user);
		if(loginedUser!=null){
			session.setAttribute("loginedUser", loginedUser);
			return todoUtil.prepareModel(loginedUser);
		}
		else
			throw new TodoLoginException(LOGIN_ERROR);
	}
	
	@ExceptionHandler(TodoLoginException.class)
	public ModelAndView handleSavingError(TodoLoginException ex){
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("user", new User());
		model.put("errorMessage", ex.getMessage());
		return new ModelAndView("home",model);
	}

}
