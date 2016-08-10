package com.demo.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.springboot.domain.Todo;
import com.demo.springboot.domain.User;
import com.demo.springboot.service.TodoService;

@Controller
@RequestMapping(value="/todo")
public class TodoController {
	
	public static final String SESSION_USER = "loginedUser";
	public static final String PROMPT_LOGIN = "loginedUser";
	
	@Autowired
	TodoService taskService;
	
	@Autowired
	TodoUtil todoUtil;
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ModelAndView updateTask(@ModelAttribute("updateTodo") Todo updateTodo, @PathVariable("id") long id, HttpSession session){
		taskService.updateTask(updateTodo.getStatus(),id);
		return todoUtil.prepareModel((User) session.getAttribute(SESSION_USER));
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView addTodo(@ModelAttribute("todo") Todo todo, HttpSession session){		
		User loginedUser = (User) session.getAttribute("loginedUser");
		if(loginedUser == null){
			Map<String,Object> model = new HashMap<String, Object>();
			model.put("user", new User());
			model.put("errorMessage", PROMPT_LOGIN);
			return new ModelAndView("home",model);
		}
		todo.setUser(loginedUser);
		taskService.addTask(todo);
		return todoUtil.prepareModel((User) session.getAttribute(SESSION_USER));
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteTask(@PathVariable("id") long id, HttpSession session){
		taskService.deleteTask(id);
		return todoUtil.prepareModel((User) session.getAttribute(SESSION_USER));
	}

}
