package com.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springboot.domain.Todo;
import com.demo.springboot.domain.User;
import com.demo.springboot.service.TodoService;

@Component
public class TodoUtil {
	
	@Autowired
	TodoService todoService;
		
	public ModelAndView prepareModel(User loginedUser){		
		Map<String,Object> model = new HashMap<String, Object>();
		model.put("todos", todoService.getTasksByUser(loginedUser));
		model.put("newTodo", new Todo());
		model.put("updateTodo", new Todo());
		return new ModelAndView("mytasks",model);
	}

}
